package microservice.microtimes.adapter.in.consumer.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import microservice.microtimes.adapter.in.consumer.dto.HistoryDtoRequest;
import microservice.microtimes.adapter.in.consumer.dto.InscritoDtoRequest;
import microservice.microtimes.application.core.domain.enums.EEventSource;
import microservice.microtimes.application.core.domain.enums.ESagaStatus;

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

