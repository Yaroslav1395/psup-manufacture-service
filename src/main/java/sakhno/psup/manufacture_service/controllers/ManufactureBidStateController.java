package sakhno.psup.manufacture_service.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sakhno.psup.manufacture_service.dto.manufacture_bid.ManufactureBidStateDto;
import sakhno.psup.manufacture_service.dto.manufacture_bid.ManufactureBidStateSaveDto;
import sakhno.psup.manufacture_service.services.munufacture_bid.ManufactureBidStateService;

import java.util.List;

@RestController
@RequestMapping("api/v1/manufacture-service/manufacture-bid-states")
@RequiredArgsConstructor
@Slf4j
public class ManufactureBidStateController {
    private final ManufactureBidStateService manufactureBidStateService;

    @GetMapping
    public Mono<ResponseEntity<List<ManufactureBidStateDto>>> getAllStates() {
        return manufactureBidStateService.findAll()
                .doFirst(() -> log.info("Запрос на получение списка состояний заявок на производство"))
                .collectList()
                .map(list -> ResponseEntity.ok().body(list))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping("/state/{id}")
    public Mono<ResponseEntity<ManufactureBidStateDto>> getStateById(@PathVariable Integer id) {
        return manufactureBidStateService.findById(id)
                .doFirst(() -> log.info("Запрос на получение состояния заявки на производство по идентификатору {}", id))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
