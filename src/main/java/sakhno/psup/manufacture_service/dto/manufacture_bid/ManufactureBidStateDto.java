package sakhno.psup.manufacture_service.dto.manufacture_bid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sakhno.psup.manufacture_service.models.manufacture_bid.ManufactureBidState;
import sakhno.psup.manufacture_service.models.manufacture_bid.ManufactureBidStateEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufactureBidStateDto {
    private Integer id;
    private ManufactureBidState state;
    private String description;

    public static ManufactureBidStateDto build(ManufactureBidStateEntity  state) {
        return ManufactureBidStateDto.builder()
                .id(state.getId())
                .state(ManufactureBidState.fromState(state.getState()))
                .description(state.getDescription())
                .build();
    }
}
