package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.application.port.output.InscricaoDeleteOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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
                () -> {throw new NoSuchElementException();}
            );

        log.info("Finalizado adaptador para deletar Inscrição, com Id: {}.", id);
    }
}

