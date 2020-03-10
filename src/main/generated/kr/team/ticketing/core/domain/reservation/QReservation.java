package kr.team.ticketing.core.domain.reservation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -847514867L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final kr.team.ticketing.core.domain.generic.entity.QBaseTimeEntity _super = new kr.team.ticketing.core.domain.generic.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final kr.team.ticketing.core.domain.generic.QEmail email;

    public final NumberPath<Long> finalPrice = createNumber("finalPrice", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath memberName = createString("memberName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productName = createString("productName");

    public final ListPath<ReservationOption, QReservationOption> reservationOptions = this.<ReservationOption, QReservationOption>createList("reservationOptions", ReservationOption.class, QReservationOption.class, PathInits.DIRECT2);

    public final EnumPath<ReservationStatus> reservationStatus = createEnum("reservationStatus", ReservationStatus.class);

    public final DatePath<java.time.LocalDate> selectDate = createDate("selectDate", java.time.LocalDate.class);

    public final StringPath selectTime = createString("selectTime");

    public final StringPath tel = createString("tel");

    public final NumberPath<Integer> totalCnt = createNumber("totalCnt", Integer.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.email = inits.isInitialized("email") ? new kr.team.ticketing.core.domain.generic.QEmail(forProperty("email")) : null;
    }

}

