package kr.team.ticketing.core.domain.reservation;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static kr.team.ticketing.core.domain.reservation.QReservationOption.reservationOption;

public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReservationRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void deleteOption(Long id) {
        queryFactory
                .delete(reservationOption)
                .where(reservationOption.id.eq(id));
    }
}
