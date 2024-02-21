package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.mapper.MapperOut;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.port.output.InscricaoFindByIdOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InscricaoFindByIdAdapter implements InscricaoFindByIdOutputPort {

    private final InscricaoRepository inscricaoRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Optional<Inscricao> findById(final Long id) {

        log.info("Iniciado adaptador para consultar Inscrição por Id.");

        var inscricaoOptional = this.inscricaoRepository.findById(id)
            .map(this.mapperOut::toInscricao);

        log.info("Finalizado adaptador para consultar Inscrição por Id: {}.", id);

        return inscricaoOptional;
    }
}

