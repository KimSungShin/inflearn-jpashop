package jpabook.jpashop;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    public void testMember() throws Exception {
//        // given
//        Member member = new Member();
//        member.setUsername("kss");
//
//        // when
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedId);
//
//        // then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//
//        // findMember 와 savedMember 결과가 같다.
//        // 같은 트랙잭션 안에서 영속성컨텍스트가 같으므로 식별정보가 같으면 같은 객체로 인식한다.
//        // 영속성 컨텍스트에 같은 식별자를 가진 엔티티객체가 존재하므로 별도의 select 쿼리 없이 1차 캐싱으로 객체를 가져와버린다.
//        Assertions.assertThat(findMember).isEqualTo(member);
//    }
}