package microservice.micropagamentos.application.core.domain;

import microservice.micropagamentos.application.core.domain.enums.ESagaStatus;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public final class SagaEvent {

    private Long sagaEventId;

    private String transactionId;

    private Long inscricaoId;

    private Long inscritoId;

    private Long torneioId;

    private Long timeId;

    private Inscrito payload;

    private String source;

    private ESagaStatus status;

    private List<History> eventHistories;

    private OffsetDateTime createdAt;

    public void addToHistory(History history) {
        if (ObjectUtils.isEmpty(this.eventHistories)) {
            this.eventHistories = new ArrayList<>();
        }
        this.eventHistories.add(history);
    }

    public Long getSagaEventId() {
        return sagaEventId;
    }

    public void setSagaEventId(Long sagaEventId) {
        this.sagaEventId = sagaEventId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getInscricaoId() {
        return inscricaoId;
    }

    public void setInscricaoId(Long inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public Long getInscritoId() {
        return inscritoId;
    }

    public void setInscritoId(Long inscritoId) {
        this.inscritoId = inscritoId;
    }

    public Long getTorneioId() {
        return torneioId;
    }

    public void setTorneioId(Long torneioId) {
        this.torneioId = torneioId;
    }

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public Inscrito getPayload() {
        return payload;
    }

    public void setPayload(Inscrito payload) {
        this.payload = payload;
    }

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

    public List<History> getEventHistories() {
        return eventHistories;
    }

    public void setEventHistories(List<History> eventHistories) {
        this.eventHistories = eventHistories;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SagaEvent{" +
            "sagaEventId=" + sagaEventId +
            ", transactionId='" + transactionId + '\'' +
            ", inscricaoId=" + inscricaoId +
            ", inscritoId=" + inscritoId +
            ", torneioId=" + torneioId +
            ", timeId=" + timeId +
            ", payload=" + payload +
            ", source='" + source + '\'' +
            ", status='" + status + '\'' +
            ", eventHistories=" + eventHistories +
            ", createdAt=" + createdAt +
            '}';
    }
}

