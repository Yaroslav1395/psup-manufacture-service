package sakhno.psup.manufacture_service.services.munufacture_bid.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sakhno.psup.manufacture_service.dto.manufacture_bid.ManufactureBidStateDto;
import sakhno.psup.manufacture_service.repositories.ManufactureBidStateRepository;
import sakhno.psup.manufacture_service.services.munufacture_bid.ManufactureBidStateService;

/**
 * Реализация позволяет взаимодействовать с состояниями заявок на производство
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ManufactureBidStateServiceImpl implements ManufactureBidStateService {

    private final ManufactureBidStateRepository manufactureBidStateRepository;

    /**
     * Метод позволяет получить список всех состояний заявки на производство
     * @return - список состояний заявок на производство
     */
    @Override
    public Flux<ManufactureBidStateDto> findAll() {
        return manufactureBidStateRepository.findAll()
                .doOnSubscribe(subscription -> log.info("Поиск всех состояний заявок на производство"))
                .doOnRequest(mbs -> log.info("Преобразование списка сущностей состояний заявок в список dto"))
                .map(ManufactureBidStateDto::build)
                .doOnComplete(() -> log.info("Преобразование всех сущностей состояний заявок в список DTO завершено"));
    }

    /**
     * Метод позволяет получить состояние заявки на производство по идентификатору
     * @param id - идентификатор состояния
     * @return - состояние заявки на производство
     */
    @Override
    public Mono<ManufactureBidStateDto> findById(Integer id) {
        return manufactureBidStateRepository.findById(id)
                .doOnSubscribe(subscription -> log.info("Поиск состояния заявки на производство по идентификатору {}", id))
                .doOnRequest(mbs -> log.info("Преобразование сущности состояния заявки на производство в DTO"))
                .map(ManufactureBidStateDto::build)
                .doOnSuccess(dto -> log.info("Преобразование сущности состояния заявки на производство в DTO завершено"));
    }

}
