package microservice.microinscricoes.application.core.domain.enums;

import microservice.microinscricoes.config.exception.http_409.ConversionEnumSagaStatusFailedException;

public enum EEventSource {

    INSCRITO_SERVICE("INSCRITO_SERVICE"),

    ORCHESTRATOR("ORCHESTRATOR"),

    TIME_VALIDATION_SERVICE("TIME_VALIDATION_SERVICE"),

    PAGAMENTO_SERVICE("PAGAMENTO_SERVICE"),

    TORNEIO_SERVICE("TORNEIO_SERVICE");

    private final String value;

    EEventSource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EEventSource fromValue(String value) {
        for (EEventSource source : values()) {
            if (source.value.equalsIgnoreCase(value)) {
                return source;
            }
        }
        throw new ConversionEnumSagaStatusFailedException();
    }
}

