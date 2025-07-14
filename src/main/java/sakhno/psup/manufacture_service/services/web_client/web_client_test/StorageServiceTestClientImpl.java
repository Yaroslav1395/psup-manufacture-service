package sakhno.psup.manufacture_service.services.web_client.web_client_test;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.reactor.timelimiter.TimeLimiterOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sakhno.psup.manufacture_service.services.web_client.ServicesPoints;
import sakhno.psup.manufacture_service.utils.TraceContextUtils;

import java.util.Objects;
import java.util.concurrent.TimeoutException;

//TODO: Удалить после отладки

/**
 * При использовании конечного автомата нужно учитывать:
 * 1. Конечный автомат не работает в реактивном режиме через аннотации.
 * 2. Для WebClient в асинхронном режиме конечный автомат настраивается через методы.
 * 3. Нужно учитывать последовательность установки timeLimiter, circuitBreaker, retry. Иначе будет работать некорректно.
 * 4. Необходимо использовать retry только при идемпотентных запросах. Иначе при таймауте будет дублирование данных.
 * 5. TimeLimiter устанавливать в соответствии с документацией запрашиваемой api.
 * 6. Необходимо тонко настраивать ошибки при которых конечный автомат переходит в открытое состояние.
 * 7. Fallback метод должен быть единым, иначе цепочка timeLimiter, circuitBreaker, retry не будет корректно отрабатывать.
 */
@Service
@Slf4j
public class StorageServiceTestClientImpl implements StorageServiceTestClient {
    private final WebClient webClient;
    private final Retry retry;
    private final TimeLimiter timeLimiter;
    private final CircuitBreaker circuitBreaker;

    public StorageServiceTestClientImpl(
            @Qualifier("storageServiceWebClient") WebClient storageWebClient,
            RetryRegistry retryRegistry,
            TimeLimiterRegistry timeLimiterRegistry,
            CircuitBreakerRegistry circuitBreakerRegistry) {
        this.webClient = storageWebClient;
        this.retry = retryRegistry.retry("STORAGE-SERVICE");
        this.timeLimiter = timeLimiterRegistry.timeLimiter("STORAGE-SERVICE");
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("STORAGE-SERVICE");
    }

    @Override
    public Mono<String> successTestRequest() {
        log.info("Успешный запрос на склад: {}", Thread.currentThread().getName());
        return TraceContextUtils.withTraceParent(traceParent -> webClient
                .get()
                .uri(ServicesPoints.STORAGE_TEST_SUCCESS.getPoint())
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> {
                    System.out.println("Success received response in thread: " + Thread.currentThread().getName());
                })
                .transformDeferred(TimeLimiterOperator.of(timeLimiter))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .transformDeferred(RetryOperator.of(retry))
                .onErrorResume(Exception.class, this::fallback)
        );
    }

    @Override
    public Mono<String> errorTestRequest() {
        return TraceContextUtils.withTraceParent(traceParent -> webClient
                .get()
                .uri(ServicesPoints.STORAGE_TEST_ERROR.getPoint())
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> {
                    System.out.println("Timeout received response in thread: " + Thread.currentThread().getName());
                })
                .transformDeferred(TimeLimiterOperator.of(timeLimiter))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .transformDeferred(RetryOperator.of(retry))
                .onErrorResume(Exception.class, this::fallback)
        );
    }

    @Override
    public Mono<String> timeoutTestRequest() {
        return TraceContextUtils.withTraceParent(traceParent -> webClient
                .get()
                .uri(ServicesPoints.STORAGE_TEST_TIMEOUT.getPoint())
                .retrieve()
                .bodyToMono(String.class)
                .transformDeferred(TimeLimiterOperator.of(timeLimiter))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .transformDeferred(RetryOperator.of(retry))
                .onErrorResume(Exception.class, this::fallback)
        );
    }

    @Override
    public Mono<String> badRequestTestRequest() {
        return TraceContextUtils.withTraceParent(traceParent -> webClient
                .get()
                .uri(ServicesPoints.STORAGE_TEST_BAD_REQUEST.getPoint())
                .retrieve()
                .bodyToMono(String.class)
        );
    }

    @Override
    public Mono<String> unprocessableEntityTestRequest() {
        return TraceContextUtils.withTraceParent(traceParent -> webClient
                .get()
                .uri(ServicesPoints.STORAGE_TEST_UNPROCESSABLE_ENTITY.getPoint())
                .retrieve()
                .bodyToMono(String.class)
        );
    }

    @Override
    public Mono<String> forbiddenTestRequest() {
        return TraceContextUtils.withTraceParent(traceParent -> webClient
                .get()
                .uri(ServicesPoints.STORAGE_TEST_FORBIDDEN.getPoint())
                .retrieve()
                .bodyToMono(String.class)
        );
    }

    @Override
    public Mono<String> notFoundTestRequest() {
        return TraceContextUtils.withTraceParent(traceParent -> webClient
                .get()
                .uri(ServicesPoints.STORAGE_TEST_NOT_FOUND.getPoint())
                .retrieve()
                .bodyToMono(String.class)
        );
    }

    @Override
    public Mono<String> unauthorizedTestRequest() {
        return TraceContextUtils.withTraceParent(traceParent -> webClient
                .get()
                .uri(ServicesPoints.STORAGE_TEST_UNAUTHORIZED.getPoint())
                .retrieve()
                .bodyToMono(String.class)
        );
    }

    @Override
    public Mono<String> tracingSuccessTestRequest() {
        log.info("Отработка успешного запроса на склад c трассировкой");
        return TraceContextUtils.withTraceParent(traceParent -> webClient
                    .get()
                    .uri(ServicesPoints.TRACE_STORAGE_TEST_SUCCESS.getPoint())
                    .headers(headers -> TraceContextUtils.setTraceToHeaders(traceParent, headers))
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnNext(response -> log.info("Success received response in thread: {}", Thread.currentThread().getName()))
                    .transformDeferred(TimeLimiterOperator.of(timeLimiter))
                    .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                    .transformDeferred(RetryOperator.of(retry))
                    .onErrorResume(Exception.class, this::fallback)
        );
    }

    public Mono<String> fallback(Throwable t) {
        if (t instanceof TimeoutException) {
            System.out.println("Circuit breaker fallback triggered: " + t.getClass().getSimpleName());
            return Mono.just("Сработал лимит времени при запросе на сервис хранилища. Причина: " + t.getMessage());
        }
        System.out.println("Circuit breaker fallback triggered: " + t.getClass().getSimpleName());
        return Mono.just("Сработал конечный автомат при запросе на сервис хранилища. Причина: " + t.getMessage());
    }
}
