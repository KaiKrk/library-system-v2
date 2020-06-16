package com.OC.librarybatch;

import com.OC.librarybatch.Entity.AuthDetails;
import com.OC.librarybatch.Entity.Booking;
import com.OC.librarybatch.Service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class BatchBookingServiceTest {

    @Autowired
    BookingService bookingService;

    @Test
    public void testFindAllActiveBooking() throws IOException, JSONException {
        List<Booking> activeBookings =  bookingService.findAllActiveBooking();
        if (activeBookings.size() > 1){
            Assert.assertNotEquals(activeBookings.get(0),"Terminee");
        }
    }



    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();


    @Test
    public void getJwtForBatchService() throws JSONException, JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("username", "annalibraryoc@gmail.com");
        personJsonObject.put("password", "123");
        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);

        AuthDetails authDetails = restTemplate.
                postForObject("http://localhost:8080/authenticate", request, AuthDetails.class);
        System.out.println(request.getHeaders().toString());
        System.out.println(authDetails);
        Assert.assertTrue(authDetails.getToken() != null);
    }


}
