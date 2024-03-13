package microservice.microtimes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.mapper.MapperOut;
import microservice.microtimes.adapter.out.repository.TimeRepository;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.port.output.TimeFindOutputPort;
import microservice.microtimes.config.exception.http_404.TimeNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TimeFindAdapter implements TimeFindOutputPort {

    private final TimeRepository timeRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Time find(final Long id) {

        log.info("Iniciado adaptador para consultar Time por Id.");

        var timeEncontrado = this.timeRepository.findById(id)
            .map(this.mapperOut::toTime)
            .orElseThrow(() -> new TimeNotFoundException(id));

        log.info("Finalizado adaptador para consultar Time por Id: {}.", timeEncontrado);

        return timeEncontrado;
    }
}

