package microservice.orchestrator.adapter.out.repository.entity;

import microservice.orchestrator.application.core.domain.History;
import microservice.orchestrator.application.core.domain.Inscrito;
import microservice.orchestrator.application.core.domain.enums.EEventSource;
import microservice.orchestrator.application.core.domain.enums.ESagaStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public final class SagaEventEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long sagaEventId;

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

}

