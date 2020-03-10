package kr.team.ticketing.domain.reservation;

import kr.team.ticketing.config.JpaConfig;
import kr.team.ticketing.core.domain.reservation.ReservationRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(JpaConfig.class)
@DataJpaTest
public class ReservationTest {
    @Autowired
    ReservationRepository reservationRepository;


}