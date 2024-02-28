package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.repository.TimeRepository;
import microservice.microinscricoes.adapter.mapper.MapperOut;
import microservice.microinscricoes.application.core.domain.Time;
import microservice.microinscricoes.application.port.output.TimeSaveOutputPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TimeSaveAdapter implements TimeSaveOutputPort {

    private final TimeRepository timeRepository;

    private final MapperOut mapperOut;

    @Override
    public Time save(Time time) {

        log.info("Iniciado adaptador para salvar Time.");

        var timeSaved = Optional.ofNullable(time)
            .map(this.mapperOut::toTimeEntity)
            .map(this.timeRepository::save)
            .map(this.mapperOut::toTime)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Time {}.", timeSaved);

        return timeSaved;
    }
}

