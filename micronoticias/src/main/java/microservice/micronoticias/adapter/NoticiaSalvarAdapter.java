package microservice.micronoticias.adapter;

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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Notícia, com título: {}.", noticiaSalva.getTitulo());

        return noticiaSalva;
    }

    private NoticiaEntity managedEditorias(NoticiaEntity noticiaEntity) {

        Set<EditoriaEntity> editoriasManaged = new HashSet<>();

        noticiaEntity.getEditorias().forEach(editoria -> {
            if (editoria.getId() == null) {
                editoriasManaged.add(editoria);
            }
            if (editoria.getId() != null) {
                var editoriaManaged = this.buscarEditoriaNoDatabase(editoria);
                BeanUtils.copyProperties(editoria, editoriaManaged, "id");
                editoriasManaged.add(editoriaManaged);
            }
        });

        noticiaEntity.setEditorias(editoriasManaged);
        return noticiaEntity;
    }

    // TODO Não precisa disso - transformar quem chama e usar ifPresent()
    private EditoriaEntity buscarEditoriaNoDatabase(EditoriaEntity editoriaEntity) {
        var idEditoria = editoriaEntity.getId();

        return this.editoriaRepository.findById(idEditoria)
            .orElseThrow(() -> new EditoriaNaoEncontradaException(idEditoria));
    }
}

