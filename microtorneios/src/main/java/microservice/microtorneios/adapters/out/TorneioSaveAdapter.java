package microservice.microtorneios.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.repository.TimeRepository;
import microservice.microtorneios.adapters.out.repository.TorneioRepository;
import microservice.microtorneios.adapters.out.repository.entity.TimeEntity;
import microservice.microtorneios.adapters.out.repository.entity.TorneioEntity;
import microservice.microtorneios.adapters.mapper.MapperOut;
import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.port.output.TorneioSaveOutputPort;
import microservice.microtorneios.config.exception.http_404.TimeNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TorneioSaveAdapter implements TorneioSaveOutputPort {

    private final TorneioRepository torneioRepository;

    private final TimeRepository timeRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public Torneio save(Torneio torneio) {

        log.info("Iniciado adaptador para salvar Torneio.");

        var torneioSaved = Optional.ofNullable(torneio)
            .map(this.mapperOut::toTorneioEntity)
            .map(entity -> this.linkarTorneioAosTimes(entity, torneio))
            .map(this.torneioRepository::save)
            .map(this.mapperOut::toTorneio)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Torneio: {}.", torneioSaved);

        return torneioSaved;
    }

    private TorneioEntity linkarTorneioAosTimes(TorneioEntity torneioEntity, Torneio torneio) {

        if (torneio.getTimes() != null) {
            Set<TimeEntity> timesEntity = new HashSet<>();

            torneio.getTimes().forEach(time -> {
                var timeIntentoryEntity = this.timeRepository.findById(time.getId())
                    .orElseThrow(() -> new TimeNotFoundException(time.getId()));
                timesEntity.add(timeIntentoryEntity);
            });

            torneioEntity.setTimes(timesEntity);
        }

        return torneioEntity;
    }
}

