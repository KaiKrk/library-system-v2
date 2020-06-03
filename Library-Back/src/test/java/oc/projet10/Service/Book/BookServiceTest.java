package oc.projet10.Service.Book;

import oc.projet10.Entity.Book;
import oc.projet10.Service.BookService;
import oc.projet10.Service.BookingService;
import oc.projet10.bean.BookDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookingService bookingService;


    @Test
    public void testReturnAllBooksExceptTheReservatedOnes2() {
        String email = "JS@email.fr";
        List<BookDto> notReservatedBookList = bookService.findBooksNotReservated(1);
        List<BookDto> reservatedBookList = bookService.findReservatedBooks(1);
        List<BookDto> allBooksInLibrary = bookService.findAll();
        System.out.println("notReservatedBookList" + notReservatedBookList.size() + "reservatedBookList"+reservatedBookList.size()+"allBooksInLibrary"+allBooksInLibrary.size());
        assertEquals(allBooksInLibrary.size(),notReservatedBookList.size()+reservatedBookList.size());

    }

    @Test
    public void testFindAllBooks(){
        bookService.findAll();
        assertTrue(bookService.findAll() != null);
    }
}
