package sakhno.psup.manufacture_service.events.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageTestEvent {
    private Long id;
    private String message;
}
