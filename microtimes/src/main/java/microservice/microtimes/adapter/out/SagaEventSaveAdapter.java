package microservice.microtimes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.mapper.MapperOut;
import microservice.microtimes.adapter.out.repository.ValidationRepository;
import microservice.microtimes.application.core.domain.ValidationModel;
import microservice.microtimes.application.port.output.SagaEventSaveOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventSaveAdapter implements SagaEventSaveOutputPort {

    private final ValidationRepository validationRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public ValidationModel save(ValidationModel validationModel) {

        log.info("Iniciado adaptador para salvar Success-Validation.");

        var validationSaved = Optional.ofNullable(validationModel)
            .map(this.mapperOut::toValidationEntity)
            .map(this.validationRepository::save)
            .map(this.mapperOut::toValidationModel)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Success-Validation: {}.", validationSaved);

        return validationSaved;
    }
}

