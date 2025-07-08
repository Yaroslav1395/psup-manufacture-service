package sakhno.psup.manufacture_service.controllers.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import sakhno.psup.manufacture_service.services.web_client.web_client_test.StorageServiceTestClient;

@RestController
@RequestMapping("api/v1/manufacture-service/test/storage")
@RequiredArgsConstructor
public class StorageTestController {
    private final StorageServiceTestClient storageServiceTestClient;

    @GetMapping("/success")
    public ResponseEntity<Mono<String>> storageSuccess() {
        System.out.println("success: " + Thread.currentThread().getName());
        return ResponseEntity.ok(storageServiceTestClient.successTestRequest());
    }

    @GetMapping("/timeout")
    public ResponseEntity<Mono<String>> storageTimeout() {
        System.out.println("timeout: " + Thread.currentThread().getName());
        return ResponseEntity.ok(storageServiceTestClient.timeoutTestRequest());
    }

    @GetMapping("/error")
    public ResponseEntity<Mono<String>> storageError() {
        return ResponseEntity.ok(storageServiceTestClient.errorTestRequest());
    }

    @GetMapping("/bad-request")
    public ResponseEntity<Mono<String>> storageBadRequest() {
        return ResponseEntity.ok(storageServiceTestClient.badRequestTestRequest());
    }

    @GetMapping("/unprocessable-entity")
    public ResponseEntity<Mono<String>> storageUnprocessableEntity() {
        return ResponseEntity.ok(storageServiceTestClient.unprocessableEntityTestRequest());
    }

    @GetMapping("/forbidden")
    public ResponseEntity<Mono<String>> storageForbidden() {
        return ResponseEntity.ok(storageServiceTestClient.forbiddenTestRequest());
    }

    @GetMapping("/not-found")
    public ResponseEntity<Mono<String>> storageNotFound() {
        return ResponseEntity.ok(storageServiceTestClient.notFoundTestRequest());
    }

    @GetMapping("/unauthorized")
    public ResponseEntity<Mono<String>> storageUnauthorized() {
        return ResponseEntity.ok(storageServiceTestClient.unauthorizedTestRequest());
    }
}
