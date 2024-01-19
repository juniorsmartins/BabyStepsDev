package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.port.output.EditoriaDeletarPorIdOutputPort;
import microservice.micronoticias.config.exception.http_404.EditoriaNaoEncontradaException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EditoriaDeletarPorIdAdapter implements EditoriaDeletarPorIdOutputPort {

    private final EditoriaRepository editoriaRepository;

    @Transactional
    @Override
    public void deletarPorId(final Long id) {

        log.info("Iniciado adaptador para deletar Editoria por Id.");

        this.editoriaRepository.findById(id)
            .ifPresentOrElse(this.editoriaRepository::delete,
                () -> {throw new EditoriaNaoEncontradaException(id);}
            );

        log.info("Finalizado adaptador para deletar Editoria por Id: {}.", id);
    }
}

