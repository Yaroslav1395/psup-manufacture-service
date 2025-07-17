package sakhno.psup.manufacture_service.controllers.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sakhno.psup.manufacture_service.dto.ManufactureKafkaTestDto;
import sakhno.psup.manufacture_service.services.kafka.kafka_test.KafkaTestService;

@RestController
@RequestMapping("api/v1/manufacture-service/test/kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaTestController {

    private final KafkaTestService kafkaTestService;

    @PostMapping
    public ResponseEntity<String> sendTestMessageToTopicManufactureTestEvent(
            @RequestBody ManufactureKafkaTestDto manufactureKafkaTestDto) {
        log.info("Запрос на отправку сообщения в очередь");
        return ResponseEntity.ok(kafkaTestService.sendTestMessageToTopicManufactureTestEvent(manufactureKafkaTestDto));
    }

}
