package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.adapter.out.mapper.EditoriaMapperOut;
import microservice.micronoticias.adapter.out.mapper.NoticiaMapperOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.output.NoticiaUpdateOutputPort;
import microservice.micronoticias.config.exception.http_404.EditoriaNaoEncontradaException;
import microservice.micronoticias.config.exception.http_404.NoticiaNotFoundException;
import microservice.micronoticias.config.exception.http_500.FailedToUpdateNewsException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticiaUpdateAdapter implements NoticiaUpdateOutputPort {

    private final NoticiaRepository noticiaRepository;

    private final EditoriaRepository editoriaRepository;

    private final NoticiaMapperOut noticiaMapperOut;

    private final EditoriaMapperOut editoriaMapperOut;

    @Transactional
    @Override
    public Noticia update(Noticia noticia) {

        log.info("Iniciado adaptador para atualizar Notícia.");

        var noticiaAtual = Optional.ofNullable(noticia.getId())
            .map(this::searchNews)
            .map(entity -> this.overwriteValues(entity, noticia))
            .map(this.noticiaMapperOut::toNoticia)
            .orElseThrow(FailedToUpdateNewsException::new);

        log.info("Finalizado adaptador para atualizar Notícia por Id: {}.", noticiaAtual.getId());

        return noticiaAtual;
    }

    private NoticiaEntity searchNews(final Long id) {
        return this.noticiaRepository.findById(id)
            .orElseThrow(() -> new NoticiaNotFoundException(id));
    }

    private NoticiaEntity overwriteValues(NoticiaEntity noticiaEntity, final Noticia noticia) {
        BeanUtils.copyProperties(noticia, noticiaEntity, "id", "editorias");
        this.overrideEditorialValues(noticiaEntity, noticia);
        return noticiaEntity;
    }

    private void overrideEditorialValues(NoticiaEntity noticiaEntity, Noticia noticia) {

        Set<EditoriaEntity> editoriasManaged = noticia.getEditorias().stream()
            .map(editoria -> {
                if (editoria.getId() == null) {
                    return this.editoriaMapperOut.toEditoriaEntity(editoria);
                } else {
                    return this.editoriaRepository.findById(editoria.getId())
                        .map(entity -> {
                            BeanUtils.copyProperties(editoria, entity, "id");
                            return entity;
                        })
                        .orElseThrow(() -> new EditoriaNaoEncontradaException(editoria.getId()));
                }
            })
            .collect(Collectors.toSet());

        noticiaEntity.setEditorias(editoriasManaged);
    }
}

