package microservice.microinscricoes.adapter.out.specs;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import microservice.microinscricoes.adapter.in.dto.request.InscricaoFiltroDto;
import microservice.microinscricoes.adapter.out.repository.entity.InscricaoEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class InscricoesSpecification {

    public static Specification<InscricaoEntity> consultarDinamicamente(InscricaoFiltroDto filtro) {

        return ((root, query, criteriaBuilder) -> {

            var pesquisa = new ArrayList<Predicate>();

            if (ObjectUtils.isNotEmpty(filtro.id())) {
                addParametroId(filtro.id(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.torneioId())) {
                addParametroTorneioId(filtro.torneioId(), root, criteriaBuilder, pesquisa);
            }

            return criteriaBuilder.and(pesquisa.toArray(new Predicate[0]));
        });
    }

    private static void addParametroId(
            String ids, Root<InscricaoEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = Stream.of(ids.trim().split(","))
            .map(Long::parseLong)
            .map(valor -> criteriaBuilder.equal(root.get("id"), valor))
            .toList();

        pesquisa.add(criteriaBuilder.or(parametros.toArray(new Predicate[0])));
    }

    private static void addParametroTorneioId(
            String torneioIds, Root<InscricaoEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = Stream.of(torneioIds.trim().split(","))
            .map(Long::parseLong)
            .map(valor -> criteriaBuilder.equal(root.get("id"), valor))
            .toList();

        pesquisa.add(criteriaBuilder.or(parametros.toArray(new Predicate[0])));
    }
}

