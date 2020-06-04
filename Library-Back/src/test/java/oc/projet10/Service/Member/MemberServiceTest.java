package oc.projet10.Service.Member;

import oc.projet10.Entity.Member;
import oc.projet10.Service.MemberService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    Member testMember = new Member();

    @BeforeAll
    public void initEntities(){
        testMember.setAdmin(false);
        testMember.setEmail("test@email.fr");
        testMember.setName("testeur");
        testMember.setPassword("123");
        testMember.setSurname("test");
    }

    @Test
    public void testSaveMember(){
    Member savedMember = memberService.save(testMember);
    Member savedMemberInDb = memberService.getMemberById(savedMember.getId());
    assertEquals(savedMember.getEmail(),savedMemberInDb.getEmail());
    assertEquals(savedMember.getName(),savedMemberInDb.getName());

    }

    @Test
    public void testFindAllMember(){
        assertTrue(memberService.findAll() != null);
    }

    @Test
    public void testGetOneMemberGivenEmail(){
        String email = "annalibraryoc@gmail.com";
        assertEquals(memberService.getMember(email).getEmail(),email);

    }

    @Test
    public void testGetOneMemberGivenId(){
        int id = 3;
        assertEquals(memberService.getMemberById(id).getId(),id);

    }

}
