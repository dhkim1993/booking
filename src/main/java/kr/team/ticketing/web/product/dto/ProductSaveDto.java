package kr.team.ticketing.web.product.dto;

import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Option;
import kr.team.ticketing.core.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSaveDto {

    //카테고리
    private Long categoryId;
    //상품필드
    private String name;
    private String description;
    private int count;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

    //옵션
    private String option1;
    private String option2;
    private String option3;
    private int price1;
    private int price2;
    private int price3;

    //InformationByDate
    private String  firstTime;
    private String secondTime;
    private String thirdTime;
    private String fourthTime;

    public Product toEntity(List<Option> options, List<InfoByDate> infoByDates) {
        return Product.builder()
                .categoryId(categoryId)
                .name(name)
                .description(description)
                .count(count)
                .startDate(startDate)
                .endDate(endDate)
                .location(location)
                .infoByDates(infoByDates)
                .options(options)
                .build();
    }
    public Product toEntity(List<Option> options) {
        return Product.builder()
                .categoryId(categoryId)
                .name(name)
                .description(description)
                .count(count)
                .startDate(startDate)
                .endDate(endDate)
                .location(location)
                .options(options)
                .build();
    }
}
