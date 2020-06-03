package oc.projet10.Service;

import oc.projet10.Entity.Book;
import oc.projet10.Repository.BookRepository;
import oc.projet10.Repository.BookingRepository;
import oc.projet10.bean.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    public List<BookDto> findAll() {
        List<BookDto> bookDtoList = bookListToDto( bookRepository.findAll());
        return bookDtoList;
    }
//
//    public List<BookDto> findAllExceptReservated(String email){
//        List<Book> bookList = bookRepository.findAll();
//        List <Book> reservatedBook = bookingService.getMemberReservatedBooks(email);
//        for (Book book : reservatedBook
//             ) {
//            if (bookList.contains(book)){
//                System.out.println("-1");
//                bookList.remove(book);
//            }
//        }
//        System.out.println("email " + email);
//        System.out.println("Tous les livres " + bookList.size() + "Nombre de livre reserve " + reservatedBook.size());
//
//        List<BookDto> bookDtoList = bookListToDto(bookList);
//        System.out.println("la liste retour " + bookDtoList.size());
//        return bookDtoList;
//    }

    public List<BookDto> findBooksNotReservated(int memberId){

        List<BookDto> bookDtoList = bookListToDto(bookRepository.findBookExceptReservatedOnes(memberId));
        return bookDtoList;
    }

    public List<BookDto> findReservatedBooks(int memberId){
        List<BookDto> reservatedBookList = bookListToDto(bookRepository.findReservatedBook(memberId));
        return  reservatedBookList;
    }

    public Book save(Book book) {
       return bookRepository.save(book);
    }

    public List<BookDto> bookListToDto(List<Book> bookList){
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book: bookList) {
            bookDtoList.add(new BookDto(book));
        }
        return bookDtoList;
    }

    public Book findBookById( int id){
        return bookRepository.findBookById(id);
    }

    public void delete(Book book){bookRepository.delete(book);}
}
