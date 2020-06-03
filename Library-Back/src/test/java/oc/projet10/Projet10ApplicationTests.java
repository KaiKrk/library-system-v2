package oc.projet10;

import com.sun.mail.smtp.SMTPTransport;
import oc.projet10.Entity.Book;
import oc.projet10.Entity.Booking;
import oc.projet10.Service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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




}
