package microservice.microinscricoes.adapter.out.producer.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import microservice.microinscricoes.adapter.out.producer.dto.HistoryDtoRequest;
import microservice.microinscricoes.adapter.out.producer.dto.InscritoDtoRequest;
import microservice.microinscricoes.application.core.domain.enums.EEventSource;
import microservice.microinscricoes.application.core.domain.enums.ESagaStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class SagaEventRequest {

    private Long id;

    private String transactionId;

    private Long inscricaoId;

    private Long inscritoId;

    private Long torneioId;

    private Long timeId;

    private InscritoDtoRequest payload;

    private EEventSource source;

    private ESagaStatus status;

    private List<HistoryDtoRequest> eventHistories;

    private OffsetDateTime createdAt;
}

