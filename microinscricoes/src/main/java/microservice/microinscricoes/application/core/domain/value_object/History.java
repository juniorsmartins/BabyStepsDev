package microservice.microinscricoes.application.core.domain.value_object;

import microservice.microinscricoes.application.core.domain.enums.EEventSource;
import microservice.microinscricoes.application.core.domain.enums.ESagaStatus;

import java.time.OffsetDateTime;

public final class History {

    private EEventSource source;

    private ESagaStatus status;

    private String message;

    private OffsetDateTime createdAt;

    public EEventSource getSource() {
        return source;
    }

    public void setSource(EEventSource source) {
        this.source = source;
    }

    public ESagaStatus getStatus() {
        return status;
    }

    public void setStatus(ESagaStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

}

