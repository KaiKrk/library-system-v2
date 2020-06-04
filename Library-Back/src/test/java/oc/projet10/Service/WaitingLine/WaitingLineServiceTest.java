package oc.projet10.Service.WaitingLine;
import oc.projet10.Entity.Book;
import oc.projet10.Entity.WaitingLine;
import oc.projet10.Service.BookService;
import oc.projet10.Service.WaitingLineService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WaitingLineServiceTest {

    @Autowired
    WaitingLineService waitingLineService;

    @Autowired
    BookService bookService;

    WaitingLine testWaitingLine = new WaitingLine();
    WaitingLine testWaitingLine2 = new WaitingLine();
    WaitingLine testWaitingLine3 = new WaitingLine();
    Book book = new Book("test","test","test",77);
    Book savedBook;

    @BeforeAll
    public void initEntities(){
        savedBook = bookService.save(book);
        testWaitingLine.setBook(savedBook);
        testWaitingLine.setRegisteredDate(LocalDateTime.now());
        testWaitingLine.setStatus("Actif");
        this.testWaitingLine = waitingLineService.save(testWaitingLine);
        testWaitingLine2.setBook(savedBook);
        testWaitingLine2.setRegisteredDate(LocalDateTime.now().minusDays(1));
        testWaitingLine2.setStatus("Actif");
        this.testWaitingLine2 = waitingLineService.save(testWaitingLine2);
        testWaitingLine3.setBook(savedBook);
        testWaitingLine3.setRegisteredDate(LocalDateTime.now().minusDays(4));
        testWaitingLine3.setStatus("Actif");
        this.testWaitingLine3 = waitingLineService.save(testWaitingLine3);
    }

    @AfterAll
    void deleteEntities(){
        waitingLineService.delete(testWaitingLine);
        waitingLineService.delete(testWaitingLine2);
        waitingLineService.delete(testWaitingLine3);
        bookService.delete(this.savedBook);
    }
    @Test
    public void testGetWaitingLineGivenId(){
        waitingLineService.getWaitingLineById(testWaitingLine.getId());
        assertEquals(waitingLineService.getWaitingLineById(testWaitingLine.getId()).getStatus(),"Actif");
    }

    @Test
    public void testGetWaitingListbyBook(){
        assertTrue(waitingLineService.getWaitingListbyBook(bookService.findBookById(savedBook.getId())) != null);
    }

    @Test
    public void testGetFirstOfWaitingLine(){
        WaitingLine theFirstInWaitingLine = waitingLineService.getTheFirstOfWaitingList(savedBook);
        assertEquals(theFirstInWaitingLine.getId(),testWaitingLine3.getId());
    }

}
