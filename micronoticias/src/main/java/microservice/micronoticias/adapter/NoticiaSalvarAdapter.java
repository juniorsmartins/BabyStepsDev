package microservice.micronoticias.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.mapper.NoticiaMapperOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
//            .map(this::checarEditorias)
            .map(this.repository::save)
            .map(this.mapperOut::toNoticia)
            .orElseThrow();

//        var noticiaSalva = Optional.of(noticia)
//            .map(this::toNoticiaEntity)
//            .map(this.repository::save)
//            .map(this::toNoticia)
//            .orElseThrow();

        log.info("Finalizado adaptador para salvar Notícia, com título: {}.", noticiaSalva.getTitulo());

        return noticiaSalva;
    }

//    private NoticiaEntity checarEditorias(NoticiaEntity entity) {
//
//        var editorias = entity.getEditorias();
//        Set<EditoriaEntity> editoriasLinkadas = new HashSet<>();
//
//        editorias.stream()
//            .map(edit -> {
//                if (edit.getId() == null) {
//                    editorias.add(edit);
//                }
//                var editoria = editoriaRepository.findById(edit.getId())
//                    .orElseThrow(() -> new EditoriaNaoEncontradaException(edit.getId()));
//                editorias.add(editoria);
//                return true;
//            });
//
//    }

//    public NoticiaEntity toNoticiaEntity(Noticia noticia) {
//        Set<EditoriaEntity> editorias = new HashSet<>();
//        noticia.getEditorias().forEach(editoria -> editorias.add(toEditoriaEntity(editoria)));
//
//        return NoticiaEntity.builder()
//                .chapeu(noticia.getChapeu())
//                .titulo(noticia.getTitulo())
//                .linhaFina(noticia.getLinhaFina())
//                .lide(noticia.getLide())
//                .corpo(noticia.getCorpo())
//                .autorias(noticia.getAutorias())
//                .fontes(noticia.getFontes())
//                .editorias(editorias)
//                .build();
//    }
//
//    public Noticia toNoticia(NoticiaEntity entity) {
//        Set<Editoria> editorias = new HashSet<>();
//        entity.getEditorias().forEach(editoriaEntity -> editorias.add(toEditoria(editoriaEntity)));
//
//        var noticia = new Noticia();
//        noticia.setId(entity.getId());
//        noticia.setChapeu(entity.getChapeu());
//        noticia.setTitulo(entity.getTitulo());
//        noticia.setLinhaFina(entity.getLinhaFina());
//        noticia.setLide(entity.getLide());
//        noticia.setCorpo(entity.getCorpo());
//        noticia.setAutorias(entity.getAutorias());
//        noticia.setFontes(entity.getFontes());
//        noticia.setEditorias(editorias);
//
//        return noticia;
//    }
//
//    public EditoriaEntity toEditoriaEntity(Editoria editoria) {
//
//        return EditoriaEntity.builder()
//                .id(editoria.getId())
//                .nomenclatura(editoria.getNomenclatura())
//                .descricao(editoria.getDescricao())
//                .build();
//    }
//
//    public Editoria toEditoria(EditoriaEntity editoriaEntity) {
//
//        var editoria = new Editoria();
//        editoria.setId(editoriaEntity.getId());
//        editoria.setNomenclatura(editoriaEntity.getNomenclatura());
//        editoria.setDescricao(editoriaEntity.getDescricao());
//
//        return editoria;
//    }
}

