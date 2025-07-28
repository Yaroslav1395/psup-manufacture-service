package sakhno.psup.manufacture_service.dto.manufacture_bid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufactureBidStateSaveDto {
    private String state;
    private String description;
}
