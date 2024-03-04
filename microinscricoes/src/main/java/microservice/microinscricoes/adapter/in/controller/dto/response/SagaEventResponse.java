package microservice.microinscricoes.adapter.in.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import microservice.microinscricoes.adapter.out.producer.dto.HistoryDtoRequest;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class SagaEventResponse {

    private Long id;

    private String transactionId;

    private Long inscricaoId;

    private Long inscritoId;

    private Long torneioId;

    private Long timeId;

    private InscritoCreateDtoOut payload;

    private String source;

    private String status;

    private List<HistoryDtoResponse> eventHistories;

    private OffsetDateTime createdAt;
}

