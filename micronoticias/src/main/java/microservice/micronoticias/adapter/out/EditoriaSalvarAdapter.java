package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.mapper.EditoriaMapperOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.output.EditoriaSalvarOutputPort;
import microservice.micronoticias.config.exception.http_500.EditoriaSalvarAdapterException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EditoriaSalvarAdapter implements EditoriaSalvarOutputPort {

    private final EditoriaRepository editoriaRepository;

    private final EditoriaMapperOut mapperOut;

    @Transactional
    @Override
    public Editoria salvar(Editoria editoria) {

        log.info("Iniciado adaptador para salvar Editoria, com nomenclatura: {}.", editoria.getNomenclatura());

        var resposta = Optional.of(editoria)
            .map(this.mapperOut::toEditoriaEntity)
            .map(this.editoriaRepository::save)
            .map(this.mapperOut::toEditoria)
            .orElseThrow(EditoriaSalvarAdapterException::new);

        log.info("Finalizado adaptador para salvar Editoria, com nomenclatura: {}.", editoria.getNomenclatura());

        return resposta;
    }
}

