package microservice.microtimes.adapter.in.consumer.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

