package oc.projet10.Service.Book;

import oc.projet10.Entity.Book;
import oc.projet10.Repository.BookRepository;
import oc.projet10.Service.BookService;
import oc.projet10.Service.BookingService;
import oc.projet10.bean.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    BookRepository bookRepository;


    @Test
    public void testReturnAllBooksExceptTheReservatedOnes() {
        int memberId = 1;
        List<BookDto> notReservatedBookList = bookService.findBooksNotReservated(memberId);
        List<BookDto> reservatedBookList = bookService.findReservatedBooks(memberId);
        List<BookDto> allBooksInLibrary = bookService.findAll();
        System.out.println("notReservatedBookList" + notReservatedBookList.size() + "reservatedBookList"+reservatedBookList.size()+"allBooksInLibrary"+allBooksInLibrary.size());
        assertEquals(allBooksInLibrary.size(),notReservatedBookList.size()+reservatedBookList.size());

    }

    @Test
    public void testFindAllBooks(){
        bookService.findAll();
        assertTrue(bookService.findAll() != null);
    }

    @Test
    public void testSaveBook(){
        Book book = new Book("test","test","test",100);
        Book savedBook = bookService.save(book);
        assertEquals(bookService.findBookById(savedBook.getId()).getName(),"test");
        assertEquals(bookService.findBookById(savedBook.getId()).getAuthor(),"test");
        bookService.delete(savedBook);
    }


}
