package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.port.input.InscricaoDeleteInputPort;
import microservice.microinscricoes.application.port.output.InscricaoDeleteOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Optional;

public class InscricaoDeleteUseCase implements InscricaoDeleteInputPort {

    private static final Logger log = LoggerFactory.getLogger(InscricaoDeleteUseCase.class);

    private final InscricaoDeleteOutputPort inscricaoDeleteOutputPort;

    public InscricaoDeleteUseCase(InscricaoDeleteOutputPort inscricaoDeleteOutputPort) {
        this.inscricaoDeleteOutputPort = inscricaoDeleteOutputPort;
    }

    @Override
    public void deleteById(final Long id) {

        log.info("Iniciado serviço para deletar Inscrição.");

        Optional.ofNullable(id)
            .ifPresentOrElse(this.inscricaoDeleteOutputPort::deleteById,
                () -> {throw new NoSuchElementException();}
            );

        log.info("Finalizado serviço para deletar Produto com id: {}.", id);
    }
}

