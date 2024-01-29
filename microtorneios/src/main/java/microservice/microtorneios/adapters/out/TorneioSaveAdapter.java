package microservice.microtorneios.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.repository.TimeInventoryRepository;
import microservice.microtorneios.adapters.out.repository.TorneioRepository;
import microservice.microtorneios.adapters.out.repository.entity.TimeInventoryEntity;
import microservice.microtorneios.adapters.out.repository.entity.TorneioEntity;
import microservice.microtorneios.adapters.out.repository.mapper.TorneioOutMapper;
import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.port.output.TorneioSaveOutputPort;
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

    private final TimeInventoryRepository timeInventoryRepository;

    private final TorneioOutMapper torneioOutMapper;

    @Transactional
    @Override
    public Torneio save(Torneio torneio) {

        log.info("Iniciado adaptador para salvar Torneio.");

        var torneioSaved = Optional.ofNullable(torneio)
                .map(torn -> {
                    System.out.println("\n\n ----- 2 ----- " + torn + " -----\n");
                    return torn;
                })
            .map(this.torneioOutMapper::toTorneioEntity)
                .map(torn -> {
                    System.out.println("\n\n ----- 3 ----- " + torn + " -----\n");
                    return torn;
                })
            .map(entity -> this.linkarTorneioAosTimes(entity, torneio))
            .map(this.torneioRepository::save)
                .map(torn -> {
                    System.out.println("\n\n ----- 4 ----- " + torn + " -----\n");
                    return torn;
                })
            .map(this.torneioOutMapper::toTorneio)
                .map(torn -> {
                    System.out.println("\n\n ----- 5 ----- " + torn + " -----\n");
                    return torn;
                })
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Torneio, com nome: {}.", torneioSaved.getNome());

        return torneioSaved;
    }

    private TorneioEntity linkarTorneioAosTimes(TorneioEntity torneioEntity, Torneio torneio) {
        Set<TimeInventoryEntity> timesEntity = new HashSet<>();

        torneio.getTimes().forEach(time -> {
            var timeIntentoryEntity = this.timeInventoryRepository.findById(time.getId())
                .orElseThrow();
            timesEntity.add(timeIntentoryEntity);
        });

        torneioEntity.setTimes(timesEntity);
        return torneioEntity;
    }
}

