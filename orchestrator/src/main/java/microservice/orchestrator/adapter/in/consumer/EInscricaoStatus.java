package microservice.orchestrator.adapter.in.consumer;

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

