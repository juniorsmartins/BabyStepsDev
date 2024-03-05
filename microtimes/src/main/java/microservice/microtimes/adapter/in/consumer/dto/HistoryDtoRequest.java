package microservice.microtimes.adapter.in.consumer.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class HistoryDtoRequest {

    private String source;

    private String status;

    private String message;

    private OffsetDateTime createdAt;
}

