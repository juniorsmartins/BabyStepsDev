package microservice.microtorneios.adapters.in.consumer.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import microservice.microtorneios.adapters.in.consumer.dto.HistoryDtoRequest;
import microservice.microtorneios.adapters.in.consumer.dto.InscritoDtoRequest;

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

    private String source;

    private String status;

    private List<HistoryDtoRequest> eventHistories;

    private OffsetDateTime createdAt;
}

