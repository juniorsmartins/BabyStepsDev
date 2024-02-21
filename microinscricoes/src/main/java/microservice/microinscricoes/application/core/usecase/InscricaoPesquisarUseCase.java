package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.filtro.InscricaoFiltro;
import microservice.microinscricoes.application.port.input.InscricaoPesquisarInputPort;
import microservice.microinscricoes.application.port.output.InscricaoPesquisarOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class InscricaoPesquisarUseCase implements InscricaoPesquisarInputPort {

    private static final Logger log = LoggerFactory.getLogger(InscricaoPesquisarUseCase.class);

    private final InscricaoPesquisarOutputPort inscricaoPesquisarOutputPort;

    public InscricaoPesquisarUseCase(InscricaoPesquisarOutputPort inscricaoPesquisarOutputPort) {
        this.inscricaoPesquisarOutputPort = inscricaoPesquisarOutputPort;
    }

    @Override
    public Page<Inscricao> pesquisar(final InscricaoFiltro inscricaoFiltro, final Pageable paginacao) {

        log.info("Iniciado serviço para pesquisar inscrições.");

        var pesquisa = Optional.ofNullable(inscricaoFiltro)
            .map(filtro -> this.inscricaoPesquisarOutputPort.pesquisar(filtro, paginacao))
            .orElseThrow();

        log.info("Finalizado serviço para pesquisar inscrições.");

        return pesquisa;
    }
}

