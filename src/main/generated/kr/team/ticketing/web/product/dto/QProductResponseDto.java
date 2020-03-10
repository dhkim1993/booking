package kr.team.ticketing.web.product.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * kr.team.ticketing.web.product.dto.QProductResponseDto is a Querydsl Projection type for ProductResponseDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QProductResponseDto extends ConstructorExpression<ProductResponseDto> {

    private static final long serialVersionUID = 802264697L;

    public QProductResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> description, com.querydsl.core.types.Expression<Integer> count, com.querydsl.core.types.Expression<java.time.LocalDate> startDate, com.querydsl.core.types.Expression<java.time.LocalDate> endDate, com.querydsl.core.types.Expression<String> location, com.querydsl.core.types.Expression<Long> categoryId, com.querydsl.core.types.Expression<? extends java.util.List<kr.team.ticketing.core.domain.product.InfoByDate>> infoByDates, com.querydsl.core.types.Expression<? extends java.util.List<kr.team.ticketing.core.domain.product.Option>> options) {
        super(ProductResponseDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, java.time.LocalDate.class, java.time.LocalDate.class, String.class, long.class, java.util.List.class, java.util.List.class}, id, name, description, count, startDate, endDate, location, categoryId, infoByDates, options);
    }

}

