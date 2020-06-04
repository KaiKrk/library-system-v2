package oc.projet10.Controller;

import oc.projet10.Entity.*;
import oc.projet10.Service.BookService;
import oc.projet10.Service.BookingService;
import oc.projet10.Service.MemberService;
import oc.projet10.Service.PickupListService;
import oc.projet10.bean.BookingDto;
import oc.projet10.bean.BookingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookingController {

    Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    BookingService bookingService;

    @Autowired
    BookService bookService;

    @Autowired
    MemberService memberService;

    @Autowired
    PickupListService pickupListService;

    @PostMapping("/saveBooking")
    public ResponseEntity<BookingDto> save(@RequestBody BookingRequest bookingRequest) {
        Member member = memberService.getMemberById(bookingRequest.getMemberId());
        Book book = bookService.findBookById(bookingRequest.getBookId());

        logger.info("New Booking created by " + member.getName() + " for the book " + book.getName());
       BookingDto newBooking =  new BookingDto(bookingService.createBooking(member, book));
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @GetMapping("/allBookings")
    public ResponseEntity<List<BookingDto>> findAllBookings(){
        List<BookingDto> bookings = bookingService.findAll() ;
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/activeBooking")
    public ResponseEntity<List<BookingDto>> findActiveBookings(){
        List<BookingDto> activeBookings =  bookingService.findAllActiveBookings();
        return new ResponseEntity<>(activeBookings,HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<BookingDto> changeStatus(@RequestBody Booking booking, String status){
        Booking newBooking = bookingService.changeStatus(booking,status);
        BookingDto bookingDto = new BookingDto(bookingService.update(newBooking));
        return new ResponseEntity<>(bookingDto, HttpStatus.OK);
    }

    @PostMapping("/myBookings")
    public ResponseEntity<List<BookingDto>> MyBooking(@RequestBody BookingRequest bookingRequest){
        Member member = memberService.getMemberById(bookingRequest.getMemberId());
        List<BookingDto> myBooking = bookingService.findMyBooking(member.getEmail());
        if (myBooking.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(myBooking, HttpStatus.OK);
    }

    @PostMapping("/extendBooking")
    public ResponseEntity<BookingDto> extendDate(@RequestBody BookingRequest bookingRequest){
        System.out.println(" id " + bookingRequest.getId());
        Booking booking = bookingService.findBookingById(bookingRequest.getId());
        BookingDto bookingDto = new BookingDto(bookingService.extend(booking));
        logger.info("booking number " + booking.getId() + "is extended " );
            return new ResponseEntity<>(bookingDto, HttpStatus.OK);
        }


    @PostMapping("/endBooking")
    public void endBooking(@RequestBody BookingRequest bookingRequest) throws Exception {
        bookingService.endBooking(bookingRequest.getId());
    }

    @PostMapping("/expiredBooking")
    public void expiredBooking(@RequestBody BookingRequest bookingRequest) throws Exception {
        bookingService.changeStatus(bookingService.findBookingById(bookingRequest.getId()), BookingStatus.Retard.toString());
    }
}
