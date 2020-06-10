package com.OC.librarybatch.Service;

import com.OC.librarybatch.Entity.Booking;
import com.OC.librarybatch.Entity.BookingRequest;
import com.OC.librarybatch.Entity.Pickup;
import com.OC.librarybatch.Entity.PickupRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PickupService {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders =  new HttpHeaders();

    @Autowired
    AuthService authService;

    public List<Pickup> findAllActivePickup() throws Exception{
        httpHeaders = authService.getJwtForBatchService();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<Pickup[]> response = restTemplate.exchange(
                "http://localhost:8080/activePickups", HttpMethod.GET, httpEntity, Pickup[].class);
        List<Pickup> activePickup = Arrays.asList(response.getBody());
        System.out.println(activePickup);
         return activePickup;
    }

    public void checkDatePickups(List<Pickup> activePickups) throws Exception{
        LocalDate today =  LocalDate.now();
        for (Pickup pickup: activePickups) {
            if (pickup.getExpirationDate().compareTo(today) > 0){
                PickupRequest pickupRequest = new PickupRequest(pickup.getId());
                sendExpiredPickups(pickupRequest);
            }
        }


    }

    public void sendExpiredPickups(PickupRequest pickup) throws JSONException, JsonProcessingException {
        httpHeaders = authService.getJwtForBatchService();
        HttpEntity httpEntity = new HttpEntity(pickup,httpHeaders);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.postForEntity(
                "http://localhost:8080/expiredPickups", httpEntity, PickupRequest.class);

    }

}
