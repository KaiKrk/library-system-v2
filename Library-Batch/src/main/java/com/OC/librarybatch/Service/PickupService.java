package com.OC.librarybatch.Service;

import com.OC.librarybatch.Entity.Booking;
import com.OC.librarybatch.Entity.BookingRequest;
import com.OC.librarybatch.Entity.Pickup;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        System.out.println(httpEntity.getHeaders().toString());
        ResponseEntity<Pickup[]> response = restTemplate.exchange(
                "http://localhost:8080/activePickups", HttpMethod.GET, httpEntity, Pickup[].class);
        List<Pickup> activePickup = Arrays.asList(response.getBody());
        System.out.println(activePickup);
         return activePickup;
    }

    public void checkDatePickups(List<Pickup> activePickups) throws Exception{
        List<Pickup> expiredPickups = new ArrayList<>();
        LocalDate today =  LocalDate.now();
        for (Pickup pickup: activePickups) {
            if (pickup.getExpirationDate().compareTo(today) > 0){
                expiredPickups.add(pickup);
            }
        }
        JSONArray activePickupJsonArray = new JSONArray(activePickups);
        httpHeaders = authService.getJwtForBatchService();
        HttpEntity httpEntity = new HttpEntity(activePickupJsonArray,httpHeaders);
        System.out.println(httpEntity.getHeaders().toString());
        ResponseEntity<BookingRequest> response = restTemplate.exchange(
                "http://localhost:8080/expiredPickups", HttpMethod.POST, httpEntity, BookingRequest.class);

    }

}
