package sakhno.psup.manufacture_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import sakhno.psup.manufacture_service.models.manufacture_bid.ManufactureBidEntity;

public interface ManufactureBidRepository extends ReactiveCrudRepository<ManufactureBidEntity, Long> {

    Flux<ManufactureBidEntity> findAllByProductId(Long productId);
}
