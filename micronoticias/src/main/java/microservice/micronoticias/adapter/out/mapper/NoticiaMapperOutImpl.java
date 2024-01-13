package microservice.micronoticias.adapter.out.mapper;

import lombok.RequiredArgsConstructor;
import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.core.domain.Noticia;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NoticiaMapperOutImpl implements NoticiaMapperOut {

    private final EditoriaMapperOut editoriaMapperOut;

    @Override
    public NoticiaEntity toNoticiaEntity(Noticia noticia) {
        Set<EditoriaEntity> editorias = new HashSet<>();
        noticia.getEditorias().forEach(editoria -> editorias.add(this.editoriaMapperOut.toEditoriaEntity(editoria)));

        return NoticiaEntity.builder()
            .chapeu(noticia.getChapeu())
            .titulo(noticia.getTitulo())
            .linhaFina(noticia.getLinhaFina())
            .lide(noticia.getLide())
            .corpo(noticia.getCorpo())
            .autorias(noticia.getAutorias())
            .fontes(noticia.getFontes())
            .editorias(editorias)
            .build();
    }

    @Override
    public Noticia toNoticia(NoticiaEntity entity) {
        Set<Editoria> editorias = new HashSet<>();
        entity.getEditorias().forEach(editoriaEntity -> editorias.add(this.editoriaMapperOut.toEditoria(editoriaEntity)));

        var noticia = new Noticia();
        noticia.setId(entity.getId());
        noticia.setChapeu(entity.getChapeu());
        noticia.setTitulo(entity.getTitulo());
        noticia.setLinhaFina(entity.getLinhaFina());
        noticia.setLide(entity.getLide());
        noticia.setCorpo(entity.getCorpo());
        noticia.setAutorias(entity.getAutorias());
        noticia.setFontes(entity.getFontes());
        noticia.setEditorias(editorias);

        return noticia;
    }
}
