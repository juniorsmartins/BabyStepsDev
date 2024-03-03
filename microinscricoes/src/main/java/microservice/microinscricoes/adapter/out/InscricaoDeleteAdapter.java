package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.application.port.output.InscricaoDeleteOutputPort;
import microservice.microinscricoes.config.exception.http_404.InscricaoNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InscricaoDeleteAdapter implements InscricaoDeleteOutputPort {

    private final InscricaoRepository inscricaoRepository;

    @Transactional
    @Override
    public void deleteById(final Long id) {

        log.info("Iniciado adaptador para deletar Inscrição.");

        this.inscricaoRepository.findById(id)
            .ifPresentOrElse(this.inscricaoRepository::delete,
                () -> {throw new InscricaoNotFoundException(id);}
            );

        log.info("Finalizado adaptador para deletar Inscrição, com Id: {}.", id);
    }
}

