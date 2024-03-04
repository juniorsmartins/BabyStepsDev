package microservice.microinscricoes.adapter.in.controller.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class FiltersDtoEvent {

    private Long id;

    private String transactionId;
}

