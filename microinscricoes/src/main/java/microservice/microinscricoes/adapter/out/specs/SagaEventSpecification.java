package microservice.microinscricoes.adapter.out.specs;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import microservice.microinscricoes.adapter.in.controller.dto.request.FiltersDtoEvent;
import microservice.microinscricoes.adapter.out.repository.entity.SagaEventEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class SagaEventSpecification {

    private SagaEventSpecification() {}

    public static Specification<SagaEventEntity> consultarDinamicamente(FiltersDtoEvent filtro) {

        return ((root, query, criteriaBuilder) -> {

            var pesquisa = new ArrayList<Predicate>();

            if (ObjectUtils.isNotEmpty(filtro.getId())) {
                addParametroId(filtro.getId(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getTransactionId())) {
                addParametroTorneioId(filtro.getTransactionId(), root, criteriaBuilder, pesquisa);
            }

            return criteriaBuilder.and(pesquisa.toArray(new Predicate[0]));
        });
    }

    private static void addParametroId(Long id, Root<SagaEventEntity> root, CriteriaBuilder criteriaBuilder,
                                       List<Predicate> pesquisa) {
        pesquisa.add(criteriaBuilder.equal(root.get("id"), id));
    }

    private static void addParametroTorneioId(String transactionId, Root<SagaEventEntity> root,
                                              CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {
        pesquisa.add(criteriaBuilder.equal(root.get("transactionId"), transactionId));
    }
}

