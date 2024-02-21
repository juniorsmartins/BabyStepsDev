package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.mapper.MapperOut;
import microservice.microinscricoes.adapter.out.repository.TimeRepository;
import microservice.microinscricoes.application.core.domain.Time;
import microservice.microinscricoes.application.port.output.TimeFindByIdOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TimeFindByIdAdapter implements TimeFindByIdOutputPort {

    private final TimeRepository timeRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Optional<Time> findById(final Long id) {

        log.info("Iniciado adaptador para consultar Time por Id.");

        var timeOptional = this.timeRepository.findById(id)
            .map(this.mapperOut::toTime);

        log.info("Finalizado adaptador para consultar Time por Id: {}.", id);

        return timeOptional;
    }
}

