package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.mapper.InscricaoMapperOut;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.port.output.InscricaoSaveOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InscricaoSaveAdapter implements InscricaoSaveOutputPort {

    private final InscricaoRepository inscricaoRepository;

    private final InscricaoMapperOut inscricaoMapperOut;

    @Transactional
    @Override
    public Inscricao save(Inscricao inscricao) {

        log.info("Iniciado adaptador para salvar abertura de período de inscrições.");

        var inscricaoOpenSave = Optional.ofNullable(inscricao)
            .map(this.inscricaoMapperOut::toInscricaoEntity)
            .map(this.inscricaoRepository::save)
            .map(this.inscricaoMapperOut::toInscricao)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar abertura de período de inscrições, com Id: {}.", inscricaoOpenSave.getId());

        return null;
    }
}

