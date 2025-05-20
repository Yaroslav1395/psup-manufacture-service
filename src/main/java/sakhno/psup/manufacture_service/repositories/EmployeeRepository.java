package sakhno.psup.manufacture_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import sakhno.psup.manufacture_service.models.EmployeeEntity;

public interface EmployeeRepository extends ReactiveCrudRepository<EmployeeEntity, Long> {
}
