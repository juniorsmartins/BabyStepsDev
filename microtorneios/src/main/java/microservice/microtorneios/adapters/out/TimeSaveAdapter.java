package microservice.microtorneios.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.mapper.MapperOut;
import microservice.microtorneios.adapters.out.repository.TimeRepository;
import microservice.microtorneios.application.core.domain.Time;
import microservice.microtorneios.application.port.output.TimeSaveOutputPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TimeSaveAdapter implements TimeSaveOutputPort {

    private final TimeRepository timeInventoryRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public void save(Time time) {

        log.info("Iniciado adaptador para salvar Time.");

        var timeSaved = Optional.ofNullable(time)
            .map(this.mapperOut::toTimeEntity)
            .map(this.timeInventoryRepository::save)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Time: {}.", timeSaved);
    }
}

