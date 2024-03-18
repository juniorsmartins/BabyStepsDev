package microservice.orchestrator.adapter.in.consumer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import microservice.orchestrator.application.core.domain.enums.EEventSource;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class HistoryDtoRequest {

    private EEventSource source;

    private String status;

    private String message;

    private OffsetDateTime createdAt;
}

