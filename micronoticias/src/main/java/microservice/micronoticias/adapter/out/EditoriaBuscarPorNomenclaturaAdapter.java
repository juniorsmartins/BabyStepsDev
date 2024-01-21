package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.mapper.EditoriaMapperOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.output.EditoriaBuscarPorNomenclaturaOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EditoriaBuscarPorNomenclaturaAdapter implements EditoriaBuscarPorNomenclaturaOutputPort {

    private final EditoriaRepository editoriaRepository;

    private final EditoriaMapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Optional<Editoria> buscarPorNomenclatura(final String nomenclatura) {

        log.info("Iniciado adaptador para buscar Editoria.");

        var editoriaEncontrada = this.editoriaRepository.findByNomenclatura(nomenclatura);
        if (editoriaEncontrada.isEmpty()) {
            return Optional.empty();
        }

        var editoria = this.mapperOut.toEditoria(editoriaEncontrada.get());

        log.info("Finalizado adaptador para buscar Editoria por Nomenclatura: {}", nomenclatura);

        return Optional.of(editoria);
    }
}

