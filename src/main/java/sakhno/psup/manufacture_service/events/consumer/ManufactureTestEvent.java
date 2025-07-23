package sakhno.psup.manufacture_service.events.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManufactureTestEvent {
    private Long id;
    private String message;
}
