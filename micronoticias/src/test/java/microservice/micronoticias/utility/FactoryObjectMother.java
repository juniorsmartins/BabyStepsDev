package microservice.micronoticias.utility;

import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public final class FactoryObjectMother {

    private static FactoryObjectMother singletonFactory;

    private FactoryObjectMother() { }

    public static synchronized FactoryObjectMother singleton() {
        if (singletonFactory == null) {
            singletonFactory = new FactoryObjectMother();
        }
        return singletonFactory;
    }

    public NoticiaEntity.NoticiaEntityBuilder gerarNoticiaEntityBuilder(int quantiaChapeu,
                                                                int quantiaTitulo, int quantiaLinhaFina,
                                                                int quantiaLide, int quantiaCorpo) {
        return NoticiaEntity.builder()
                .chapeu(gerarString(quantiaChapeu))
                .titulo(gerarString(quantiaTitulo))
                .linhaFina(gerarString(quantiaLinhaFina))
                .lide(gerarString(quantiaLide))
                .corpo(gerarString(quantiaCorpo));
    }

    private String gerarString(int numeroCaracteres) {
        return RandomStringUtils.randomAlphabetic(numeroCaracteres);
    }

    public List<String> gerarListString(int quantidade, int numeroCaracteres) {

        List<String> lista = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            lista.add(RandomStringUtils.randomAlphabetic(numeroCaracteres));
        }
        return lista;
    }
}

