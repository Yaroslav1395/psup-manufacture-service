package sakhno.psup.manufacture_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import sakhno.psup.manufacture_service.services.web_client.web_client_test.StorageServiceTestClient;

@RestController
@RequestMapping("api/v1/manufacture-service/test")
@RequiredArgsConstructor
public class TestController {
    private final StorageServiceTestClient storageServiceTestClient;
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
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/storage/success")
    public ResponseEntity<Mono<String>> storageSuccess() {
        System.out.println("success: " + Thread.currentThread().getName());
        return ResponseEntity.ok(storageServiceTestClient.successTestRequest());
    }

    @GetMapping("/storage/timeout")
    public ResponseEntity<Mono<String>> storageTimeout() {
        System.out.println("timeout: " + Thread.currentThread().getName());
        return ResponseEntity.ok(storageServiceTestClient.timeoutTestRequest());
    }

    @GetMapping("/storage/error")
    public ResponseEntity<Mono<String>> storageError() {
        return ResponseEntity.ok(storageServiceTestClient.errorTestRequest());
    }

    @GetMapping("/storage/bad-request")
    public ResponseEntity<Mono<String>> storageBadRequest() {
        return ResponseEntity.ok(storageServiceTestClient.badRequestTestRequest());
    }

    @GetMapping("/storage/unprocessable-entity")
    public ResponseEntity<Mono<String>> storageUnprocessableEntity() {
        return ResponseEntity.ok(storageServiceTestClient.unprocessableEntityTestRequest());
    }

    @GetMapping("/storage/forbidden")
    public ResponseEntity<Mono<String>> storageForbidden() {
        return ResponseEntity.ok(storageServiceTestClient.forbiddenTestRequest());
    }

    @GetMapping("/storage/not-found")
    public ResponseEntity<Mono<String>> storageNotFound() {
        return ResponseEntity.ok(storageServiceTestClient.notFoundTestRequest());
    }

    @GetMapping("/storage/unauthorized")
    public ResponseEntity<Mono<String>> storageUnauthorized() {
        return ResponseEntity.ok(storageServiceTestClient.unauthorizedTestRequest());
    }
}
