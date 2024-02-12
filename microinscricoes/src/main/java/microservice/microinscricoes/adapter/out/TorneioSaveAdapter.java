package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.repository.TorneioRepository;
import microservice.microinscricoes.adapter.out.repository.mapper.MapperOut;
import microservice.microinscricoes.application.core.domain.Torneio;
import microservice.microinscricoes.application.port.output.TorneioSaveOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TorneioSaveAdapter implements TorneioSaveOutputPort {

    private final TorneioRepository torneioRepository;

    private final MapperOut mapper;

    @Transactional
    @Override
    public void save(Torneio torneio) {

        log.info("Iniciado adaptador para salvar Torneio.");

        Optional.ofNullable(torneio)
            .map(this.mapper::toTorneioEntity)
            .map(this.torneioRepository::save)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Torneio {}.", torneio);
    }
}
