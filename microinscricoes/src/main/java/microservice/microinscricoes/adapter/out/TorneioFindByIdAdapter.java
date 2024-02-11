package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.repository.TorneioRepository;
import microservice.microinscricoes.adapter.out.repository.mapper.MapperOut;
import microservice.microinscricoes.application.core.domain.Torneio;
import microservice.microinscricoes.application.port.output.TorneioFindByIdOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TorneioFindByIdAdapter implements TorneioFindByIdOutputPort {

    private final TorneioRepository torneioRepository;

    private final MapperOut mapper;

    @Transactional(readOnly = true)
    @Override
    public Optional<Torneio> findById(final Long id) {

        log.info("Iniciado adaptador para consultar Torneio por Id.");

        var torneioOptional = this.torneioRepository.findById(id)
            .map(this.mapper::toTorneio);

        log.info("Finalizado adaptador para consultar Torneio por Id: {}.", id);

        return torneioOptional;
    }
}

