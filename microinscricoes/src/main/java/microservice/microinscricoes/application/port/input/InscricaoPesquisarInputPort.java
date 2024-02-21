package microservice.microinscricoes.application.port.input;

import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.filtro.InscricaoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InscricaoPesquisarInputPort {

    Page<Inscricao> pesquisar(InscricaoFiltro inscricaoFiltro, Pageable paginacao);
}

