package sakhno.psup.manufacture_service.services.employee.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import sakhno.psup.manufacture_service.models.EmployeeEntity;
import sakhno.psup.manufacture_service.repositories.EmployeeRepository;
import sakhno.psup.manufacture_service.services.employee.EmployeeService;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Flux<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
