package sakhno.psup.manufacture_service.services.kafka.kafka_test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sakhno.psup.manufacture_service.dto.ManufactureKafkaTestDto;
import sakhno.psup.manufacture_service.events.producer.StorageTestEvent;
import sakhno.psup.manufacture_service.services.kafka.KafkaTopicNames;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaTestServiceImpl implements KafkaTestService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public String sendTestMessageToTopicManufactureTestEvent(ManufactureKafkaTestDto manufactureKafkaTestDto) {
        log.info("Отработка отправки сообщения в очередь: {}", manufactureKafkaTestDto.getMessage());

        Long id = new Random().nextLong();

        log.info("Сгенерирован ID: {}", id);

        StorageTestEvent storageTestEvent = StorageTestEvent.builder()
                .id(id)
                .message(manufactureKafkaTestDto.getMessage())
                .build();

        kafkaTemplate.send(KafkaTopicNames.MANUFACTURE_TEST_TOPIC.getTopicName(), id.toString(), storageTestEvent)
                .thenAccept(result -> log.info("Сообщение отправлено с офсетом: {}", result.getRecordMetadata().offset()))
                .exceptionally(ex -> {
                    log.error("Сообщение не отправлено", ex);
                    return null;
                });

        return "Сообщение отправлено: " + id;
    }
}
