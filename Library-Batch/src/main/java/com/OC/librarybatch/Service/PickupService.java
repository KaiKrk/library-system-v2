package com.OC.librarybatch.Service;

import com.OC.librarybatch.Entity.BookingRequest;
import com.OC.librarybatch.Entity.Pickup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PickupService {

    RestTemplate restTemplate = new RestTemplate();
    public List<Pickup> findAllActivePickup(){
         ResponseEntity<Pickup[]> responseEntity =
                 restTemplate.getForEntity("http://localhost:8080/activePickups", Pickup[].class);
         List<Pickup> activePickups = Arrays.asList(responseEntity.getBody());
         return activePickups;
    }

    public void checkDatePickups(List<Pickup> activePickups){
        List<Pickup> expiredPickups = new ArrayList<>();
        LocalDate today =  LocalDate.now();
        for (Pickup pickup: activePickups) {
            if (pickup.getExpirationDate().compareTo(today) > 0){
                expiredPickups.add(pickup);
            }
        }
        restTemplate.postForEntity("http://localhost:8080/expiredPickups",expiredPickups, ResponseEntity.class);
    }

    public HttpStatus endBooking(BookingRequest bookingRequest){
        ResponseEntity<BookingRequest> responseEntity =
                restTemplate.postForEntity("http://localhost:8080/activePickups",bookingRequest, BookingRequest.class);
        return responseEntity.getStatusCode();
    }
}
