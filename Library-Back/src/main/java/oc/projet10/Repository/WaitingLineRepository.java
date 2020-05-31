package oc.projet10.Repository;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.Member;
import oc.projet10.Entity.WaitingLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaitingLineRepository extends JpaRepository<WaitingLine, Integer> {

     List<WaitingLine> findAllByBookAndStatusOrderByRegisteredDateAsc(Book book, String status);

     List<WaitingLine> findAllByMemberAndStatus(Member member, String status);

     WaitingLine findById(int id);

}
