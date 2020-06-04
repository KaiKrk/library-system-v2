package oc.projet10.Service;

import oc.projet10.Entity.*;
import oc.projet10.Repository.BookingRepository;
import oc.projet10.bean.BookingDto;
import oc.projet10.bean.MailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class BookingService {

    @Autowired
    private MailDetails mailDetails;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookService bookService;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    WaitingLineService waitingLineService;

    @Autowired
    PickupListService pickupListService;

    @Autowired
    EmailService emailService;

    public List<BookingDto> findAll(){
        List<BookingDto> bookingDtoList = bookingListToDto(bookingRepository.findAll());
        return bookingDtoList;
    }

    public Booking findBookingById(int id){
        return bookingRepository.findBookingById(id);
    }

    public List<BookingDto> findAllActiveBookings(){
        List<BookingDto> activebookingDtoList = bookingListToDto( bookingRepository.findAllByStatus("Actif"));
          return activebookingDtoList;
    }

    public Booking createBooking(Member member, Book book){
        Booking booking = new Booking();
        LocalDate today =  LocalDate.now();
        LocalDate futureDate = LocalDate.now().plusMonths(1);
        booking.setBorrowingDate(today);
        booking.setReturnDate(futureDate);
        booking.setRenewable(true);
        booking.setMembre(member);
        booking.setBook(book);
        booking.setStatus(BookingStatus.Actif.toString());
        book.setCopies(book.getCopies()-1);
        bookService.save(book);
       return saveBooking(booking);
    }

    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public Booking update(Booking booking){
        return bookingRepository.save(booking);
    }

    public List<Booking> findAllByBookOrderByDate(Book book){
        return bookingRepository.findAllByBookOrderByReturnDate(book);
    }


    public List<BookingDto> findMyBooking(String email){
    Member member = memberService.getMember(email);
    List<BookingDto> MyBookings = bookingListToDto( bookingRepository.findAllByMember(member));
    return MyBookings;
    }

    public List<BookingDto> bookingListToDto(List<Booking> bookingList){
        List<BookingDto> bookingDtoList = new ArrayList<>();
        for (Booking booking : bookingList) {
            bookingDtoList.add(new BookingDto(booking));
        }
        return bookingDtoList;
    }

    public Booking changeStatus(Booking booking,String status){

//        if (status.equalsIgnoreCase(BookingStatus.Actif.toString())){
//            booking.setStatus(status);
//        } else if (status.equalsIgnoreCase(BookingStatus.Prolongee.toString())){
//            booking.setStatus(status);
//        } else if (status.equalsIgnoreCase(BookingStatus.Terminee.toString())){
//            booking.setStatus(status);
//        }
        if (status.equalsIgnoreCase(BookingStatus.Retard.toString())){
            booking.setStatus(status);
            booking.setRenewable(false);
        } else{
            booking.setStatus(status);
        }

        return bookingRepository.save(booking);
    }

    
//    public void checkDate(List<Booking> bookings) throws MessagingException {
//        LocalDate today =  LocalDate.now();
//        for (Booking booking: bookings) {
//            if (booking.getReturnDate().compareTo(today) > 0){
//                booking.setStatus(BookingStatus.Retard.toString());
//                sendMail(booking.getMembre().getEmail(), booking.getBook().getName());
//            }
//        }
//    }
    
    public Booking extend(Booking booking){
        // #2 cannot extend booking if renewable is false
        if(booking.getRenewable() == true){
            booking.setReturnDate(LocalDate.now().plusMonths(1));
            booking.setStatus(BookingStatus.Prolongee.toString());
            booking.setRenewable(false);
            System.out.println(booking +" extend");
        }
        return update(booking);
    }

    public void endBooking(int id) throws Exception {
    Booking booking = bookingRepository.findBookingById(id);
    Book returnedBook = booking.getBook();
    List<WaitingLine> waitingListsForThisBook = waitingLineService.getWaitingListbyBook(returnedBook);
        System.out.println(waitingListsForThisBook.size());
    if (waitingListsForThisBook.size() == 0  && returnedBook.getCopies() > 0){
        booking.setStatus(BookingStatus.Terminee.toString());
        booking.setRenewable(false);
        returnedBook.setCopies(returnedBook.getCopies()+1);
        bookService.save(returnedBook);
        update(booking);
    } else {
        WaitingLine waitingLinefirst = waitingLineService.getTheFirstOfWaitingList(returnedBook);
        String email = waitingLinefirst.getMember().getEmail();
        emailService.sendEmailForPickup(email, waitingLinefirst.getBook());
        waitingLineService.updateStatus(waitingLinefirst);
        pickupListService.waitingLineToPickupLine(waitingLinefirst);
    }
    
    }

    public void deleteBooking(int id){
        bookingRepository.deleteById(id);
    }

}

