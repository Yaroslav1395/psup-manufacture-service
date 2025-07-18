package sakhno.psup.manufacture_service.controllers.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import sakhno.psup.manufacture_service.services.web_client.web_client_test.StorageServiceTestClient;

@RestController
@RequestMapping("api/v1/manufacture-service/test/storage/tracing")
@RequiredArgsConstructor
@Slf4j
public class TracingTestController {
    private final StorageServiceTestClient storageServiceTestClient;

    @GetMapping("/success")
    public Mono<ResponseEntity<String>> storageSuccess() {
        log.info("Запрос на получение успеха с трассировкой");
        return storageServiceTestClient.tracingSuccessTestRequest()
                .map(ResponseEntity::ok);
    }
}
