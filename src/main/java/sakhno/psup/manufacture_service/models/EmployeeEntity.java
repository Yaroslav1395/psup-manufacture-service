package sakhno.psup.manufacture_service.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    private Long id;
    private String fio;
}
