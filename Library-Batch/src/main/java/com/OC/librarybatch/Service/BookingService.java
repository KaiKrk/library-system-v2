package com.OC.librarybatch.Service;

import com.OC.librarybatch.Entity.Booking;
import com.OC.librarybatch.Entity.MailDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

    @Autowired
    private MailDetails mailDetails;

    public List<Booking> findAllByStatus() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://localhost:8080/activeBooking";

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        httpClient.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            System.out.println(response.toString());
            List bookingList = Arrays.asList(mapper.readValue(response.toString(), Booking[].class));
            return bookingList;
        }
    }

    public void checkDate(List<Booking> bookings) throws MessagingException {
        LocalDate today =  LocalDate.now();
        for (Booking booking: bookings) {
            if (booking.getReturnDate().compareTo(today) > 0){
                sendMail(booking.getMemberEmail(), booking.getBookName());
            }
        }
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
