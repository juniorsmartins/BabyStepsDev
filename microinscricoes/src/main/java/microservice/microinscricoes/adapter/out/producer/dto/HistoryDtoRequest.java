package microservice.microinscricoes.adapter.out.producer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class HistoryDtoRequest {

    private String source;

    private String status;

    private String message;

    private LocalDateTime createdAt;
}

