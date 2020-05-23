package oc.projet10.Controller;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.WaitingLine;
import oc.projet10.Service.BookService;
import oc.projet10.Service.WaitingLineService;
import oc.projet10.bean.BookDto;
import oc.projet10.bean.PickupDto;
import oc.projet10.bean.WaitingLineDto;
import oc.projet10.bean.WaitingLinePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WaitingLineController {

    @Autowired
    WaitingLineService waitingLineService   ;

    @Autowired
    BookService bookService;

    Logger logger = LoggerFactory.getLogger(BookingController.class);

    @PostMapping("/saveInWaitingLine")
    public ResponseEntity<WaitingLineDto> save(@RequestBody WaitingLine waitingLine) {
        WaitingLineDto waitingLineDto = new WaitingLineDto(waitingLineService.save(waitingLine));
        return new ResponseEntity<>(waitingLineDto, HttpStatus.CREATED);

    }

    @PostMapping("/waitingLinePosition")
    public WaitingLinePosition getPositionInWaitingLine(@RequestBody int  bookId) {
        int position =  waitingLineService.getWaitingListbyBook(bookService.findBookById(bookId)).size();
        return new WaitingLinePosition(bookService.findBookById(bookId),position);
    }
}
