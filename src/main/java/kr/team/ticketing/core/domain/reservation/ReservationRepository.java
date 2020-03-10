package kr.team.ticketing.core.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> , ReservationRepositoryCustom {
    List<Reservation> findByMemberId(Long memberId);
}
