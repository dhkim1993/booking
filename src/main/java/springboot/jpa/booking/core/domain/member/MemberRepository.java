package springboot.jpa.booking.core.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.jpa.booking.core.domain.generic.Email;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);
}
