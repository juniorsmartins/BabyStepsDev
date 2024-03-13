package microservice.microtimes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.mapper.MapperOut;
import microservice.microtimes.adapter.out.repository.TimeRepository;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.port.output.TimeSaveOutputPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TimeSaveAdapter implements TimeSaveOutputPort {

    private final TimeRepository timeRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public Time save(Time time) {

        log.info("Adaptador iniciado para salvar Time.");

        var timeSave = Optional.ofNullable(time)
            .map(this.mapperOut::toTimeEntity)
            .map(this.timeRepository::save)
            .map(this.mapperOut::toTime)
            .orElseThrow();

        log.info("Adaptador finalizado para salvar Time: {}.", timeSave);

        return timeSave;
    }
}

