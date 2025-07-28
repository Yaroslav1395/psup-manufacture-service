package sakhno.psup.manufacture_service.exceptions.all;

import lombok.Getter;

@Getter
public class EnumNotFoundException extends RuntimeException {
    private final String message;

    public EnumNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
