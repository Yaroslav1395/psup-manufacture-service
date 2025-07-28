package sakhno.psup.manufacture_service.services.munufacture_bid;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sakhno.psup.manufacture_service.dto.manufacture_bid.ManufactureBidStateDto;
import sakhno.psup.manufacture_service.dto.manufacture_bid.ManufactureBidStateSaveDto;

/**
 * Интерфейс позволяет реализовать взаимодействие с состояниями заявок на производство
 */
public interface ManufactureBidStateService {

    /**
     * Метод позволяет получить список всех состояний заявки на производство
     * @return - список состояний заявок на производство
     */
    Flux<ManufactureBidStateDto> findAll();

    /**
     * Метод позволяет получить состояние заявки на производство по идентификатору
     * @param id - идентификатор состояния
     * @return - состояние заявки на производство
     */
    Mono<ManufactureBidStateDto> findById(Integer id);
}
