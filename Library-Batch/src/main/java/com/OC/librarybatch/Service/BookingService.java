package com.OC.librarybatch.Service;

import com.OC.librarybatch.Entity.Booking;
import com.OC.librarybatch.Entity.BookingRequest;
import com.OC.librarybatch.Entity.MailDetails;
import com.OC.librarybatch.Entity.Pickup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.ws.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
public class BookingService {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    private MailDetails mailDetails;

    @Autowired
    private AuthService authService;

//    public List<Booking> findAllByStatus() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String url = "http://localhost:8080/activeBooking";
//
//        HttpURLConnection httpClient =
//                (HttpURLConnection) new URL(url).openConnection();
//
//        httpClient.setRequestMethod("GET");
//
//        try (BufferedReader in = new BufferedReader(
//                new InputStreamReader(httpClient.getInputStream()))) {
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = in.readLine()) != null) {
//                response.append(line);
//            }
//            List bookingList = Arrays.asList(mapper.readValue(response.toString(), Booking[].class));
//            return bookingList;
//        }
//    }


    public List<Booking> findAllActiveBooking() throws JSONException, JsonProcessingException {
        httpHeaders = authService.getJwtForBatchService();
        HttpEntity request = new HttpEntity(httpHeaders);
        System.out.println(request.getHeaders().toString());
        ResponseEntity<Booking[]> response = restTemplate.exchange(
                "http://localhost:8080/activeBooking", HttpMethod.GET, request, Booking[].class);
        List<Booking> activeBookings = Arrays.asList(response.getBody());

        return activeBookings;
    }

    public void checkDateBooking(List<Booking> bookings) throws Exception {
        LocalDate today =  LocalDate.now();
        for (Booking booking: bookings) {
            if (booking.getReturnDate().compareTo(today) > 0){
                System.out.println("send mail " + booking.getMember().getEmail());
                BookingRequest bookingRequest = new BookingRequest(booking.getId());
                expiredBooking(bookingRequest);
                sendMail(booking.getMember().getEmail(), booking.getBook().getName());
            }
        }
    }
    public void expiredBooking(BookingRequest bookingRequest) throws JSONException, JsonProcessingException {
//        ResponseEntity<BookingRequest> responseEntity =
//                restTemplate.postForEntity("http://localhost:8080/expiredBooking",bookingRequest, BookingRequest.class);
//        JSONObject bookingJsonObject = new JSONObject();
//        bookingJsonObject.put("id",bookingRequest.getId());
        httpHeaders = authService.getJwtForBatchService();
        HttpEntity httpEntity = new HttpEntity<>(bookingRequest, httpHeaders);
        System.out.println(bookingRequest.getId());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
         restTemplate.postForEntity(
                "http://localhost:8080/expiredBooking", httpEntity, BookingRequest.class);
    }


    public void sendMail(String recepient, String book) throws javax.mail.MessagingException {



        Properties properties = new Properties();
        properties.put("imap.googlemail.com","imaps");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailDetails.getMyAccountEmail(), mailDetails.getPassword());
            }
        });
        Message message = prepareMessage(session, mailDetails.getMyAccountEmail(), recepient, book);
        Transport.send(message);

    }
    private Message prepareMessage (Session session, String myAccountEmail, String recepient, String book ) throws MessagingException {

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(mailDetails.getSubject());
            message.setText(mailDetails.getMessage() + book);
            return message;
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }


}
