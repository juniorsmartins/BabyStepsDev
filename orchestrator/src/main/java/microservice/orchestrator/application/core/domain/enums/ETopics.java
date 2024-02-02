package microservice.orchestrator.application.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ETopics {

    START_SAGA("start-saga"),
    BASE_ORCHESTRATOR("orchestrator"),
    FINISH_SUCCESS("finish-success"),
    FINISH_FAIL("finish-fail"),
    TIME_VALIDATION_SUCCESS("time-validation-success"),
    TIME_VALIDATION_FAIL("time-validation-fail"),
    PAGAMENTO_SUCCESS("pagamento-success"),
    PAGAMENTO_FAIL("pagamento-fail"),
    TORNEIO_SUCCESS("torneio-success"),
    TORNEIO_FAIL("torneio-fail"),
    NOTIFY_ENDING("notify-ending");

    private final String topic;
}

