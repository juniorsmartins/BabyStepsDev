package microservice.microinscricoes.adapter.out.producer.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class FiltersDtoEvent {

    private Long orderId;

    private Long transactionId;
}

