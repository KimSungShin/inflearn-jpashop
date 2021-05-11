package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        // findMember 와 savedMember 결과가 같다.
        // 같은 트랙잭션 안에서 영속성컨텍스트가 같으므로 식별정보가 같으면 같은 객체로 인식한다.
        // 영속성 컨텍스트에 같은 식별자를 가진 엔티티객체가 존재하므로 별도의 select 쿼리 없이 1차 캐싱으로 객체를 가져와버린다.
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("예외가 발생해야 한다.");

    }

}