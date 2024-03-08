package microservice.micropagamentos.adapter.in.consumer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class HistoryDtoRequest {

    private String source;

    private String status;

    private String message;

    private OffsetDateTime createdAt;
}
