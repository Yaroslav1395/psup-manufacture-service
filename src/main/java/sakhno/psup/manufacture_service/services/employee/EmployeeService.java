package sakhno.psup.manufacture_service.services.employee;

import reactor.core.publisher.Flux;
import sakhno.psup.manufacture_service.models.EmployeeEntity;

public interface EmployeeService {

    Flux<EmployeeEntity> getAllEmployees();
}
