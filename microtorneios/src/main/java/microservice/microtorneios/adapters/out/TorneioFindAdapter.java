package microservice.microtorneios.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.mapper.MapperOut;
import microservice.microtorneios.adapters.out.repository.TorneioRepository;
import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.port.output.TorneioFindOutputPort;
import microservice.microtorneios.config.exception.http_404.TorneioNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TorneioFindAdapter implements TorneioFindOutputPort {

    private final TorneioRepository torneioRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Torneio find(final Long id) {

        log.info("Iniciado adaptador para consultar Torneio por Id.");

        var torneioEncontrado = this.torneioRepository.findById(id)
            .map(this.mapperOut::toTorneio)
            .orElseThrow(() -> new TorneioNotFoundException(id));

        log.info("Finalizado adaptador para consultar Torneio por Id: {}.", torneioEncontrado);

        return torneioEncontrado;
    }
}

