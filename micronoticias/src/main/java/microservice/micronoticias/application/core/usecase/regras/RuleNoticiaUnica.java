package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.application.core.domain.Noticia;
import org.springframework.stereotype.Service;

@Service
public class RuleNoticiaUnica implements RuleStrategy {

    @Override
    public void executar(Noticia noticia) {

    }
}
