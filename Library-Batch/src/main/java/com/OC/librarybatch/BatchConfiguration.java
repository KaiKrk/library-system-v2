package com.OC.librarybatch;

import com.OC.librarybatch.Entity.Booking;
import com.OC.librarybatch.Service.BookingService;
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

        @Scheduled(cron = "0 5 * * * *") // everyday at  5 am
        public void checkReturnDate()  {
            try {
               List<Booking> bookingList = bookingService.findAllByStatus();
               bookingService.checkDate(bookingList);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

}
