package oc.projet10.Repository;

import oc.projet10.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookById(int id);

    @Query(value = "select * from book where book_id in (select book_id from booking where member_id = ?1 and not status ='Terminee')", nativeQuery = true)
    List<Book> findReservatedBook(int memberId);

//    @Query("select book from Book book where book.id not in (select book.id from Booking b where b.member.id = 1 and not b.status ='Terminee')")
    @Query(value = "select * from book where book_id not in (select book_id from booking where member_id = ?1 and  not status ='Terminee')", nativeQuery = true)
    List<Book> findBookExceptReservatedOnes(int memberId);
}
