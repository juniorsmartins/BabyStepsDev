package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.repository.InscritoRepository;
import microservice.microinscricoes.adapter.mapper.MapperOut;
import microservice.microinscricoes.application.core.domain.Inscrito;
import microservice.microinscricoes.application.port.output.InscritoSaveOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InscritoSaveAdapter implements InscritoSaveOutputPort {

    private final InscritoRepository inscritoRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public Inscrito save(Inscrito inscrito) {

        log.info("Iniciado adaptador para registrar Inscrito.");

        var inscritoSave = Optional.ofNullable(inscrito)
            .map(this.mapperOut::toInscritoEntity)
            .map(this.inscritoRepository::save)
            .map(this.mapperOut::toInscrito)
            .orElseThrow();

        log.info("Finalizado adaptador para registrar Inscrito, com Id: {}.", inscritoSave.getId());

        return inscritoSave;
    }
}

