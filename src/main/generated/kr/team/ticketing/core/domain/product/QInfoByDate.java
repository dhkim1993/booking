package kr.team.ticketing.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QInfoByDate is a Querydsl query type for InfoByDate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInfoByDate extends EntityPathBase<InfoByDate> {

    private static final long serialVersionUID = 631533839L;

    public static final QInfoByDate infoByDate = new QInfoByDate("infoByDate");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final StringPath firstTime = createString("firstTime");

    public final StringPath fourthTime = createString("fourthTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quantity1 = createNumber("quantity1", Integer.class);

    public final NumberPath<Integer> quantity2 = createNumber("quantity2", Integer.class);

    public final NumberPath<Integer> quantity3 = createNumber("quantity3", Integer.class);

    public final NumberPath<Integer> quantity4 = createNumber("quantity4", Integer.class);

    public final StringPath secondTime = createString("secondTime");

    public final StringPath thirdTime = createString("thirdTime");

    public QInfoByDate(String variable) {
        super(InfoByDate.class, forVariable(variable));
    }

    public QInfoByDate(Path<? extends InfoByDate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInfoByDate(PathMetadata metadata) {
        super(InfoByDate.class, metadata);
    }

}

