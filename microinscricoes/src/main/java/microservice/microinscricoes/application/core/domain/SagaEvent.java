package microservice.microinscricoes.application.core.domain;

import microservice.microinscricoes.application.core.domain.enums.EEventSource;
import microservice.microinscricoes.application.core.domain.enums.ESagaStatus;
import microservice.microinscricoes.application.core.domain.value_object.History;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class SagaEvent {

    private Long id;

    private String transactionId;

    private Long inscricaoId;

    private Long inscritoId;

    private Long torneioId;

    private Long timeId;

    private Inscrito payload;

    private EEventSource source;

    private ESagaStatus status;

    private List<History> eventHistories;

    private OffsetDateTime createdAt;

    public void generateTransactionId() {
        if (this.transactionId == null) {
            this.transactionId = String.format("%s_%s", OffsetDateTime.now().toEpochSecond(), UUID.randomUUID());
        }
    }

    public void addToHistory(History history) {
        if (ObjectUtils.isEmpty(this.eventHistories)) {
            this.eventHistories = new ArrayList<>();
        }
        this.eventHistories.add(history);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}

