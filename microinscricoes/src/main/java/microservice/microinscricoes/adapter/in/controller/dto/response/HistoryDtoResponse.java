package microservice.microinscricoes.adapter.in.controller.dto.response;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class HistoryDtoResponse {

    private String source;

    private String status;

    private String message;

    private OffsetDateTime createdAt;
}

