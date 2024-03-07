package microservice.microtimes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.mapper.MapperOut;
import microservice.microtimes.adapter.out.repository.ValidationRepository;
import microservice.microtimes.application.core.domain.ValidationModel;
import microservice.microtimes.application.port.output.SagaEventFindOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventFindAdapter implements SagaEventFindOutputPort {

    private final ValidationRepository validationRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Optional<ValidationModel> findBySagaEventIdAndTransactionId(final Long sagaEventId, final String transactionId) {

        return this.validationRepository.findBySagaEventIdAndTransactionId(sagaEventId, transactionId)
            .map(this.mapperOut::toValidationModel);
    }
}

