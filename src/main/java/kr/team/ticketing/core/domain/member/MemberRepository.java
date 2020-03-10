package kr.team.ticketing.core.domain.member;

import kr.team.ticketing.core.domain.generic.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);
}
