package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.input.EditoriaListarInputPort;
import microservice.micronoticias.application.port.output.EditoriaListarOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EditoriaListarUseCase implements EditoriaListarInputPort {

    private static final Logger log = LoggerFactory.getLogger(EditoriaListarUseCase.class);

    private final EditoriaListarOutputPort editoriaListarOutputPort;

    public EditoriaListarUseCase(EditoriaListarOutputPort editoriaListarOutputPort) {
        this.editoriaListarOutputPort = editoriaListarOutputPort;
    }

    @Override
    public List<Editoria> listar() {

        log.info("Iniciado serviço para listar Editorias.");

        var resposta = this.editoriaListarOutputPort.listar();

        log.info("Finalizado serviço para listar Editorias.");

        return resposta;
    }
}

