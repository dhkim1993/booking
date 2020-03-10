package kr.team.ticketing.domain.member;

import kr.team.ticketing.core.domain.member.Member;
import kr.team.ticketing.core.domain.member.MemberRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @Test
    public void insert(){
        // given
        memberRepository.save(Member.builder()
                .name("김철수")
                .email("chulsu@naver.com")
                .build());

        // when
        List<Member> all = memberRepository.findAll();

        // then
        Member member = all.get(0);
        assertThat(member.getName()).isEqualTo("김철수");
        assertThat(member.getEmail().getHost()).isEqualTo("@naver.com");
        assertThat(member.getEmail().getId()).isEqualTo("chulsu");
    }


}