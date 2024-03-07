package microservice.orchestrator.application.core.domain.enums;

public enum EInscricaoStatus {

    ATIVO("Ativo"),

    INATIVO("Inativo");

    private final String status;

    EInscricaoStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

