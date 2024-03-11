package microservice.microtorneios.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.mapper.MapperOut;
import microservice.microtorneios.adapters.out.repository.TorneioRepository;
import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.port.output.TorneioSaveOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TorneioSaveAdapter implements TorneioSaveOutputPort {

    private final TorneioRepository torneioRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public Torneio save(Torneio torneio) {

        log.info("Adaptador iniciado para salvar Torneio.");

        var torneioSaved = Optional.ofNullable(torneio)
            .map(this.mapperOut::toTorneioEntity)
            .map(this.torneioRepository::save)
            .map(this.mapperOut::toTorneio)
            .orElseThrow();

        log.info("Adaptador finalizado para salvar Torneio: {}.", torneioSaved);

        return torneioSaved;
    }

}

