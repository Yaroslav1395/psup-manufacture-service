package sakhno.psup.manufacture_service.services.web_client;

import lombok.Getter;

@Getter
public enum ServicesPoints {
    STORAGE_TEST_SUCCESS("Отвечает успехом", "api/v1/storage-service/test/ok"),
    STORAGE_TEST_ERROR("Отвечает ошибкой", "api/v1/storage-service/test"),
    STORAGE_TEST_TIMEOUT("Выдает таймаут", "api/v1/storage-service/test/timeout"),
    STORAGE_TEST_BAD_REQUEST("Выдает некорректный запрос", "api/v1/storage-service/test/bad-request"),
    STORAGE_TEST_UNPROCESSABLE_ENTITY("Выдает неподдерживаемый тип объекта", "api/v1/storage-service/test/unprocessable-entity"),
    STORAGE_TEST_FORBIDDEN("Выдает ошибку сервера", "api/v1/storage-service/test/forbidden"),
    STORAGE_TEST_UNAUTHORIZED("Выдает ошибку сервера", "api/v1/storage-service/test/unauthorized"),
    STORAGE_TEST_NOT_FOUND("Выдает ошибку ненайденного ресурса", "api/v1/storage-service/test//not-found"),;

    private final String description;
    private final String point;

    ServicesPoints(String description, String point) {
        this.description = description;
        this.point = point;
    }

}
