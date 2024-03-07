package microservice.orchestrator.application.core.domain.enums;

import microservice.orchestrator.config.exception.http_409.ConversionEnumSagaStatusFailedException;

public enum ESagaStatus {

    SUCCESS("SUCCESS"),

    ROLLBACK_PENDING("ROLLBACK_PENDING"),

    FAIL("FAIL");

    private final String value;

    ESagaStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ESagaStatus fromValue(String value) {
        for (ESagaStatus status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new ConversionEnumSagaStatusFailedException();
    }
}

