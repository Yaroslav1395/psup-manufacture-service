package sakhno.psup.manufacture_service.controllers.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/manufacture-service/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private Integer count = 0;

    @GetMapping
    public ResponseEntity<String> test() {
        count++;
        System.out.println("Запрос " + count);
        throw new RuntimeException("Simulated failure");
    }

    @GetMapping("/timeout")
    public ResponseEntity<String> timeout() {
        try {
            Thread.sleep(5000); // Спим 5 секунд
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(500).body("Interrupted");
        }
        return ResponseEntity.ok("Completed without timeout");
    }

    @GetMapping("/ok")
    public Mono<ResponseEntity<String>> ok() {
        log.info("Тестовый успешный запрос на производство");
        return Mono.just(ResponseEntity.ok("OK"));
    }
}
