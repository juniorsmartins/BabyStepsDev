package microservice.orchestrator.saga;

import microservice.orchestrator.application.core.domain.enums.EEventSource;
import microservice.orchestrator.application.core.domain.enums.ESagaStatus;
import microservice.orchestrator.config.kafka.ETopics;

public class SagaHandler {

    private SagaHandler() { }

    public static final Object[][] SAGA_HANDLER = {
        {EEventSource.ORCHESTRATOR, ESagaStatus.SUCCESS, ETopics.TIME_VALIDATION_SUCCESS},
        {EEventSource.ORCHESTRATOR, ESagaStatus.FAIL, ETopics.FINISH_FAIL},

        {EEventSource.TIME_VALIDATION_SERVICE, ESagaStatus.ROLLBACK_PENDING, ETopics.TIME_VALIDATION_FAIL},
        {EEventSource.TIME_VALIDATION_SERVICE, ESagaStatus.FAIL, ETopics.FINISH_FAIL},
        {EEventSource.TIME_VALIDATION_SERVICE, ESagaStatus.SUCCESS, ETopics.PAGAMENTO_SUCCESS},

        {EEventSource.PAGAMENTO_SERVICE, ESagaStatus.ROLLBACK_PENDING, ETopics.PAGAMENTO_FAIL},
        {EEventSource.PAGAMENTO_SERVICE, ESagaStatus.FAIL, ETopics.TIME_VALIDATION_FAIL},
        {EEventSource.PAGAMENTO_SERVICE, ESagaStatus.SUCCESS, ETopics.TORNEIO_SUCCESS},

        {EEventSource.TORNEIO_SERVICE, ESagaStatus.ROLLBACK_PENDING, ETopics.TORNEIO_FAIL},
        {EEventSource.TORNEIO_SERVICE, ESagaStatus.FAIL, ETopics.PAGAMENTO_FAIL},
        {EEventSource.TORNEIO_SERVICE, ESagaStatus.SUCCESS, ETopics.FINISH_SUCCESS}
    };

    public static final int EVENT_SOURCE_INDEX = 0;

    public static final int SAGA_STATUS_INDEX = 1;

    public static final int TOPIC_INDEX = 2;

}

