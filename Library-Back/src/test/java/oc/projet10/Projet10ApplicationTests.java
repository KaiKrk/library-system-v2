package oc.projet10;

import com.sun.mail.smtp.SMTPTransport;
import oc.projet10.Entity.Book;
import oc.projet10.Entity.Booking;
import oc.projet10.Service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;


@SpringBootTest
class Projet10ApplicationTests {

    @Autowired
    BookingService bookingService;

    @Test
    void contextLoads() {
    }

   @Test
    void sendEmail() throws Exception{
       List<Booking> bookingList = bookingService.findAllByStatus();
       System.out.println(bookingList.toString());

       bookingService.checkDate(bookingList);
   }

}
