package com.OC.librarybatch;

import com.OC.librarybatch.Entity.Booking;
import com.OC.librarybatch.Entity.Pickup;
import com.OC.librarybatch.Service.BookingService;
import com.OC.librarybatch.Service.PickupService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

        @Autowired
        BookingService bookingService;

        @Autowired
        PickupService pickupService;
       // @Scheduled(cron = "0 10 * * * *") // everyday at  10 am
        @Scheduled(cron = "5 * * * * *") // everyday at  10 am
        public void checkReturnDate()  {
            System.out.println("ping");
            try {
               List<Booking> bookingList = bookingService.findAllActiveBooking();
               bookingService.checkDateBooking(bookingList);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    @Scheduled(cron = "5 * * * * *") // everyday at  10 am
    public void checkReturnDatePickup()  {
        try {
            List<Pickup> pickupList = pickupService.findAllActivePickup();
            pickupService.checkDatePickups(pickupList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
