package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.adapter.out.mapper.NoticiaMapperOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;
import microservice.micronoticias.config.exception.http_404.EditoriaNaoEncontradaException;
import microservice.micronoticias.config.exception.http_500.NoticiaSalvarAdapterException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticiaSalvarAdapter implements NoticiaSalvarOutputPort {

    private final NoticiaRepository repository;

    private final EditoriaRepository editoriaRepository;

    private final NoticiaMapperOut mapperOut;

    @Transactional
    @Override
    public Noticia salvar(Noticia noticia) {

        log.info("Iniciado adaptador para salvar Notícia, com título: {}.", noticia.getTitulo());

        var noticiaSalva = Optional.of(noticia)
            .map(this.mapperOut::toNoticiaEntity)
            .map(this::managedEditorias)
            .map(this.repository::save)
            .map(this.mapperOut::toNoticia)
            .orElseThrow(NoticiaSalvarAdapterException::new);

        log.info("Finalizado adaptador para salvar Notícia, com título: {}.", noticiaSalva.getTitulo());

        return noticiaSalva;
    }

    private NoticiaEntity managedEditorias(NoticiaEntity noticiaEntity) {

        Set<EditoriaEntity> editoriasManaged = noticiaEntity.getEditorias().stream()
            .map(editoria -> {
                if (editoria.getId() == null) {
                    return editoria;
                } else {
                    return this.editoriaRepository.findById(editoria.getId())
                        .map(edit -> {
                            BeanUtils.copyProperties(editoria, edit, "id");
                            return edit;
                        })
                        .orElseThrow(() -> new EditoriaNaoEncontradaException(editoria.getId()));
                }
            })
            .collect(Collectors.toSet());

        noticiaEntity.setEditorias(editoriasManaged);
        return noticiaEntity;
    }
}

