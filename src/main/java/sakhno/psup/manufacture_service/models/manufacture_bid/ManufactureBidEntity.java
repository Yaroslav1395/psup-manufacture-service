package sakhno.psup.manufacture_service.models.manufacture_bid;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Сущность отображает заявку на производство
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "manufacture_bids")
public class ManufactureBidEntity {
    @Id
    private Long id;
    private Long productId;
    private Integer productQuantity;
    private Integer manufactureStateId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
