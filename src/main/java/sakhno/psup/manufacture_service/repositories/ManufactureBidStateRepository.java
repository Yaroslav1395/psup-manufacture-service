package sakhno.psup.manufacture_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import sakhno.psup.manufacture_service.models.manufacture_bid.ManufactureBidStateEntity;

@Repository
public interface ManufactureBidStateRepository extends ReactiveCrudRepository<ManufactureBidStateEntity, Integer> {
}
