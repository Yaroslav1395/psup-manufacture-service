package sakhno.psup.manufacture_service.services.kafka.kafka_test;

import sakhno.psup.manufacture_service.dto.ManufactureKafkaTestDto;

public interface KafkaTestService {

    String sendTestMessageToTopicManufactureTestEvent(ManufactureKafkaTestDto manufactureKafkaTestDto);
}
