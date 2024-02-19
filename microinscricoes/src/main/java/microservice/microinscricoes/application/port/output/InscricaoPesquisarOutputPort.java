package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.InscricaoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InscricaoPesquisarOutputPort {

    Page<Inscricao> pesquisar(InscricaoFiltro inscricaoFiltro, Pageable paginacao);
}

