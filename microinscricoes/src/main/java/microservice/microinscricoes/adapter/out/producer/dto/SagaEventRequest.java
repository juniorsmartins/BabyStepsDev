package microservice.microinscricoes.adapter.out.producer.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class SagaEventRequest {

    private Long id;

    private Long transactionId;

    private Long inscricaoId;

    private Long inscritoId;

    private Long torneioId;

    private Long timeId;

    private OrderDtoRequest payload;

    private String source;

    private String status;

    private List<HistoryDtoRequest> eventHistories;

    private LocalDateTime createdAt;
}

