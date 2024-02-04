package microservice.microtimes.adapter.in.dto;

import microservice.microtimes.adapter.in.dto.enums.ESagaStatus;

import java.time.Instant;

public final class History {

    private String source;

    private ESagaStatus status;

    private String message;

    private Instant createdAt;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

