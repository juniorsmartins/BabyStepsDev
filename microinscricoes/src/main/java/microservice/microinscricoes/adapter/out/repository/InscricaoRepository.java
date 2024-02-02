package microservice.microinscricoes.adapter.out.repository;

import microservice.microinscricoes.adapter.out.entity.InscricaoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends MongoRepository<InscricaoEntity, Long> { }

