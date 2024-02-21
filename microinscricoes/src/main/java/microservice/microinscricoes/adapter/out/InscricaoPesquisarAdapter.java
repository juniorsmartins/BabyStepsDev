package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.adapter.out.mapper.MapperOut;
import microservice.microinscricoes.adapter.out.specs.InscricoesSpecification;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.filtro.InscricaoFiltro;
import microservice.microinscricoes.application.port.output.InscricaoPesquisarOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InscricaoPesquisarAdapter implements InscricaoPesquisarOutputPort {

    private final InscricaoRepository inscricaoRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Page<Inscricao> pesquisar(final InscricaoFiltro inscricaoFiltro, final Pageable paginacao) {

        log.info("Iniciado adaptador para pesquisar inscrições.");

        var pesquisa = Optional.ofNullable(inscricaoFiltro)
            .map(this.mapperOut::toInscricaoFiltroDto)
            .map(parametros -> this.inscricaoRepository.findAll(InscricoesSpecification.consultarDinamicamente(parametros), paginacao))
            .map(page -> page.map(this.mapperOut::toInscricao))
            .orElseThrow();

        log.info("Finalizado adaptador para pesquisar inscrições.");

        return pesquisa;
    }
}

