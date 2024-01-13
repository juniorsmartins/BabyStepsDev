package microservice.micronoticias.adapter.out.mapper;

import lombok.RequiredArgsConstructor;
import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.core.domain.Noticia;
import org.springframework.beans.BeanUtils;
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

        var noticiaEntity = new NoticiaEntity();
        BeanUtils.copyProperties(noticia, noticiaEntity, "editorias");
        noticiaEntity.setEditorias(editorias);

        return noticiaEntity;
    }

    @Override
    public Noticia toNoticia(NoticiaEntity noticiaEntity) {
        Set<Editoria> editorias = new HashSet<>();
        noticiaEntity.getEditorias().forEach(editoriaEntity -> editorias.add(this.editoriaMapperOut.toEditoria(editoriaEntity)));

        var noticia = new Noticia();
        BeanUtils.copyProperties(noticiaEntity, noticia, "editorias");
        noticia.setEditorias(editorias);

        return noticia;
    }
}
