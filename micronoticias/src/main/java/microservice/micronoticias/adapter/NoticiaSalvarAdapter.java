package microservice.micronoticias.adapter;

import lombok.RequiredArgsConstructor;
import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NoticiaSalvarAdapter {

    private final NoticiaRepository noticiaRepository;

    public NoticiaEntity salvar(NoticiaEntity entity) {

        return this.noticiaRepository.save(entity);
    }
}