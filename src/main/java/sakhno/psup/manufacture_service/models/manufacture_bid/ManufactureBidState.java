package sakhno.psup.manufacture_service.models.manufacture_bid;

import lombok.Getter;
import sakhno.psup.manufacture_service.exceptions.all.EnumNotFoundException;

@Getter
public enum ManufactureBidState {
    CREATED("Создана"),
    IN_PROGRESS("В процессе"),
    TRANSIT_TO_STORE("В пути на склад"),
    COMPLETED("Завершена"),
    CANCELED("Отменена");

    private final String description;

    ManufactureBidState(String description) {
        this.description = description;
    }

    public static ManufactureBidState fromState(String state) {
        for (ManufactureBidState enumState : ManufactureBidState.values()) {
            if (enumState.name().equalsIgnoreCase(state)) {
                return enumState;
            }
        }
        throw new EnumNotFoundException("Неизвестное состояние заявки на производство: " + state);
    }
}
