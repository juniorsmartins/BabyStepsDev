package microservice.microtimes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.out.repository.ValidationRepository;
import microservice.microtimes.application.port.output.SagaEventExistsOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventExistsAdapter implements SagaEventExistsOutputPort {

    private final ValidationRepository validationRepository;

    @Transactional(readOnly = true)
    @Override
    public Boolean existsDuplication(final Long sagaEventId, final String transactionId) {
        return this.validationRepository.existsBySagaEventIdAndTransactionId(sagaEventId, transactionId);
    }
}

