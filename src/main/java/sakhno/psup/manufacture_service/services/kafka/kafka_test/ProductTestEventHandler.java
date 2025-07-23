package sakhno.psup.manufacture_service.services.kafka.kafka_test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sakhno.psup.manufacture_service.events.consumer.ManufactureTestEvent;

@Profile({"local", "test", "prod"})
@Component
@KafkaListener(topics = "product-test-topic")
@RequiredArgsConstructor
@Slf4j
public class ProductTestEventHandler {

    @KafkaHandler
    public void handle(@Payload ManufactureTestEvent manufactureTestEvent, @Header("messageId") String id,
                       @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {
        log.info("Из очереди product-test-topic получено сообщение: {}. Ключ сообщения: {}. ID сообщения: {}",
                manufactureTestEvent.getMessage(), messageKey, id);
        log.info("Сообщение: {} + manufacture-service",manufactureTestEvent.getMessage());
    }
}
