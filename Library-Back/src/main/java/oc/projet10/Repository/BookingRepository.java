package oc.projet10.Repository;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.Booking;
import oc.projet10.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByMember(Member member);

    @Query("SELECT b from Booking b where b.status = :statusA or b.status = :statusP")
    List<Booking> findAllByStatus(String statusA, String statusP);

    Booking findBookingById(int id);

    List<Booking> findAllByBookOrderByReturnDate(Book book);

    void deleteById(int id);

}
