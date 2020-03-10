package kr.team.ticketing.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOption is a Querydsl query type for Option
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOption extends EntityPathBase<Option> {

    private static final long serialVersionUID = -2034867055L;

    public static final QOption option = new QOption("option");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath optionName = createString("optionName");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QOption(String variable) {
        super(Option.class, forVariable(variable));
    }

    public QOption(Path<? extends Option> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOption(PathMetadata metadata) {
        super(Option.class, metadata);
    }

}

