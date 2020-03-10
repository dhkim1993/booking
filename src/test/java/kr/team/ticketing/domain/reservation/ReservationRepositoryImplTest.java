package kr.team.ticketing.domain.reservation;

import kr.team.ticketing.config.QuerydslConfig;
import kr.team.ticketing.core.domain.reservation.Reservation;
import kr.team.ticketing.core.domain.reservation.ReservationOption;
import kr.team.ticketing.core.domain.reservation.ReservationRepository;
import kr.team.ticketing.service.reservation.ReservationService;
import kr.team.ticketing.web.reservation.dto.SelectedDataDto;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kimdonghyun on 03/03/2020.
 */

@DataJpaTest
@RunWith(SpringRunner.class)
@Import(QuerydslConfig.class)
@RequiredArgsConstructor
public class ReservationRepositoryImplTest {


    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void test () {
        //given
        SelectedDataDto dto = SelectedDataDto.builder()
                .productId(1L)
                .optionCount1(1)
                .optionCount2(2)
                .build();
        Long id = reservationService.selectedDataSave(dto);
        Reservation reservation = reservationRepository.findById(id).get();
        //when
        List<ReservationOption> reservationOptions = reservation.getReservationOptions();
        for (ReservationOption reservationOption : reservationOptions) {
            reservationRepository.deleteOption(reservationOption.getId());
        }
        //then
        assertThat(reservationOptions.size()).isEqualTo(0);
    }

    @Test
    public void deleteOptionTest() throws Exception {

    }
}