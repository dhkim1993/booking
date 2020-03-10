package kr.team.ticketing.core.domain.reservation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservationOption is a Querydsl query type for ReservationOption
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReservationOption extends EntityPathBase<ReservationOption> {

    private static final long serialVersionUID = 1280977378L;

    public static final QReservationOption reservationOption = new QReservationOption("reservationOption");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath optionName = createString("optionName");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QReservationOption(String variable) {
        super(ReservationOption.class, forVariable(variable));
    }

    public QReservationOption(Path<? extends ReservationOption> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservationOption(PathMetadata metadata) {
        super(ReservationOption.class, metadata);
    }

}

