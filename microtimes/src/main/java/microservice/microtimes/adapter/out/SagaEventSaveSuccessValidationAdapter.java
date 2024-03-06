package microservice.microtimes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.mapper.MapperOut;
import microservice.microtimes.adapter.out.repository.ValidationRepository;
import microservice.microtimes.application.core.domain.ValidationModel;
import microservice.microtimes.application.port.output.SagaEventSaveSuccessValidationOutputPort;
import microservice.microtimes.config.exception.http_409.SuccessValidationDuplicationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventSaveSuccessValidationAdapter implements SagaEventSaveSuccessValidationOutputPort {

    private final ValidationRepository validationRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public ValidationModel saveSuccessValidation(ValidationModel validationModel) {

        log.info("");

        var validationSaved = Optional.ofNullable(validationModel)
            .map(this::checkValidationDuplication)
            .map(this.mapperOut::toValidationEntity)
            .map(this.validationRepository::save)
            .map(this.mapperOut::toValidationModel)
            .orElseThrow();

        log.info("");

        return validationSaved;
    }

    private ValidationModel checkValidationDuplication(ValidationModel validationModel) {

        var response = Optional.ofNullable(validationModel)
            .map(model -> this.validationRepository
                .existsBySagaEventIdAndTransactionId(model.getSagaEventId(), model.getTransactionId()))
            .orElseThrow();

        if (response) {
            throw new SuccessValidationDuplicationException();
        }

        return validationModel;
    }
}

