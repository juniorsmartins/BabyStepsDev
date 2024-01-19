package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.mapper.EditoriaMapperOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.output.EditoriaListarOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EditoriaListarAdapter implements EditoriaListarOutputPort {

    private final EditoriaRepository editoriaRepository;

    private final EditoriaMapperOut editoriaMapperOut;

    @Transactional(readOnly = true)
    @Override
    public List<Editoria> listar() {

        log.info("Iniciado adaptador para listar Editorias.");

        var listaRecuperada = this.editoriaRepository.findAll()
            .stream()
            .map(this.editoriaMapperOut::toEditoria)
            .toList();

        log.info("Finalizado adaptador para listar Editorias.");

        return listaRecuperada;
    }
}

