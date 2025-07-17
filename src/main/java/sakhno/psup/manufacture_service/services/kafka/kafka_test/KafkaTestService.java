package sakhno.psup.manufacture_service.services.kafka.kafka_test;

import reactor.core.publisher.Mono;
import sakhno.psup.manufacture_service.dto.ManufactureKafkaTestDto;
import sakhno.psup.manufacture_service.events.producer.StorageTestEvent;

public interface KafkaTestService {

    String sendTestMessageToTopicManufactureTestEvent(ManufactureKafkaTestDto manufactureKafkaTestDto);
}
