package springboot.jpa.booking.core.domain.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SearchCondition {

    private int page;
    private int size;
    private Long categoryId;
    private String location;
    private int month;


    @Builder
    public SearchCondition(int page, int size, Long categoryId, String location, int month) {
        this.page = page;
        this.size = size;
        this.categoryId = categoryId;
        this.location = location;
        this.month = month;
    }
}
