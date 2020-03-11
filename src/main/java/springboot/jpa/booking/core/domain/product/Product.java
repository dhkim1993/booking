package springboot.jpa.booking.core.domain.product;

import lombok.*;
import springboot.jpa.booking.web.product.dto.ProductSaveDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private String description;

    private int count;

    private LocalDate startDate;

    private LocalDate endDate;

    private String location;

    // ================================================================================================== //

    @Column(name = "category_id")   //약한 결합
    private Long categoryId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<InfoByDate> infoByDates;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Option> options;

    // === 비즈니스 로직 === //


    public void update(ProductSaveDto productSaveDto) {
        this.name=productSaveDto.getName();
        this.description = productSaveDto.getDescription();
        this.count = productSaveDto.getCount();
        this.startDate = productSaveDto.getStartDate();
        this.endDate = productSaveDto.getEndDate();
        this.location = productSaveDto.getLocation();
    }

    @Builder
    public Product(Long categoryId, String name, String description, int count, LocalDate startDate, LocalDate endDate, String location, List<InfoByDate> infoByDates, List<Option> options) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.infoByDates=infoByDates;
        this.options = options;
    }
}
