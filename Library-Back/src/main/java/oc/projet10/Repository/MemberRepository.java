package oc.projet10.Repository;

import oc.projet10.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

   Optional <Member> findByEmail(String email);

   Member findMemberByEmail(String email);

   Member findById(int id);
}
