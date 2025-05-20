package sakhno.psup.manufacture_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import sakhno.psup.manufacture_service.models.EmployeeEntity;
import sakhno.psup.manufacture_service.services.EmployeeService;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public Flux<EmployeeEntity> getAllProducts(){
        return employeeService.getAllEmployees();
    }
}
