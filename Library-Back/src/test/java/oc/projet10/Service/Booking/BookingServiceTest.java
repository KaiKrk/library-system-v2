package oc.projet10.Service.Booking;

import ch.qos.logback.core.spi.LifeCycle;
import oc.projet10.Entity.Book;
import oc.projet10.Entity.Booking;
import oc.projet10.Entity.Member;
import oc.projet10.Service.BookService;
import oc.projet10.Service.BookingService;
import oc.projet10.Service.MemberService;
import oc.projet10.bean.BookingDto;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookingServiceTest {

    @Autowired
    BookingService bookingService;

    @Autowired
    MemberService memberService;

    @Autowired
    BookService bookService;

    Member testMember = new Member();
    Booking testBooking = new Booking();
    Book testBook = new Book();


    @BeforeAll
     void initEntities(){
        testMember = memberService.getMemberById(3);
        testBook.setId(-60);
        testBook.setCopies(100);
        testBook.setMax_copies(100);
        testBook.setAuthor("test");
        testBook.setCategory("test");
        testBook.setName("test");
        bookService.save(testBook);
        testBooking.setId(-50);
        testBooking.setMembre(testMember);
        testBooking.setBook(testBook);
        testBooking.setRenewable(true);
        testBooking.setStatus("test");
        testBooking.setBorrowingDate(LocalDate.now());
        testBooking.setReturnDate(LocalDate.now().plusDays(7));
    }

    @AfterAll
    void deleteEntities(){
        bookService.delete(testBook);
    }

    @Test
    public void testReturnAllBookings(){
        assertTrue(bookingService.findAll() != null);
    }

    @Test
    public void testReturnBookingById(){
        int id = 1;
        Booking returnedBooking = bookingService.findBookingById(id);
        assertEquals(id, returnedBooking.getId());
    }

    @Test
    public void testReturnMemberBooking(){
        String email = "JS@email.fr";
        List<BookingDto> returnedBookings = bookingService.findMyBooking(email);
        if (returnedBookings != null){
            assertTrue(email.equalsIgnoreCase(returnedBookings.get(0).getMember().getEmail()));
        }
    }

    @Test
    public void testSavingBooking(){
        bookingService.saveBooking(testBooking);
        Booking booking = bookingService.findBookingById(-50);
        assertEquals(booking,testBooking);
        bookingService.deleteBooking(-50);
    }

}
