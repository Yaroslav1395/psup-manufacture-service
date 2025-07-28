package sakhno.psup.manufacture_service.models.manufacture_bid;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import sakhno.psup.manufacture_service.dto.manufacture_bid.ManufactureBidStateDto;
import sakhno.psup.manufacture_service.dto.manufacture_bid.ManufactureBidStateSaveDto;

/**
 * Сущность отображает состояние заявки на производство. Присваивается каждой заявке.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "manufacture_bid_states")
public class ManufactureBidStateEntity {
    @Id
    private Integer id;
    private String state;
    private String description;

    public static ManufactureBidStateEntity build(ManufactureBidStateSaveDto saveStateDto) {
        return ManufactureBidStateEntity.builder()
                .state(saveStateDto.getState())
                .description(saveStateDto.getDescription())
                .build();
    }
}
