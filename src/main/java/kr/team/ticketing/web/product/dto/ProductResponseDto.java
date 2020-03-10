package kr.team.ticketing.web.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Option;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private int count;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private Long categoryId;
    private List<InfoByDate> infoByDates;
    private List<Option> options;

    @QueryProjection
    @Builder
    public ProductResponseDto(Long id, String name, String description, int count, LocalDate startDate, LocalDate endDate, String location, Long categoryId, List<InfoByDate> infoByDates, List<Option> options) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.categoryId = categoryId;
        this.infoByDates = infoByDates;
        this.options = options;
    }
}
