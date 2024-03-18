package microservice.microtimes.application.core.domain.enums;

public enum EEventSource {

    INSCRICAO_SERVICE("INSCRICAO_SERVICE"),

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

}

