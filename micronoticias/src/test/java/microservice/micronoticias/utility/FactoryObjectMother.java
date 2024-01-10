package microservice.micronoticias.utility;

import microservice.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.application.core.domain.Noticia;
import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public final class FactoryObjectMother {

    private static FactoryObjectMother singletonFactory;

    private final Faker faker = new Faker();

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

    public Noticia gerarNoticia(int quantiaChapeu, int quantiaTitulo, int quantiaLinhaFina,
                                int quantiaLide, int quantiaCorpo, int quantidadeAutoria,
                                int numeroCaracterAutoria, int quantidadeFonte, int numeroCaracterFonte) {
        var noticia = new Noticia();
        noticia.setChapeu(gerarString(quantiaChapeu));
        noticia.setTitulo(gerarString(quantiaTitulo));
        noticia.setLinhaFina(gerarString(quantiaLinhaFina));
        noticia.setLide(gerarString(quantiaLide));
        noticia.setCorpo(gerarString(quantiaCorpo));
        noticia.setAutorias(gerarListString(quantidadeAutoria, numeroCaracterAutoria));
        noticia.setFontes(gerarListString(quantidadeFonte, numeroCaracterFonte));

        return noticia;
    }

    public NoticiaCriarDtoIn.NoticiaCriarDtoInBuilder gerarNoticiaCriarDtoInBuilder() {

        return NoticiaCriarDtoIn.builder()
            .chapeu(faker.lorem().characters(2, 30))
            .titulo(faker.lorem().characters(20, 150))
            .linhaFina(faker.lorem().characters(80, 250))
            .lide(faker.lorem().characters(80, 400))
            .corpo(faker.lorem().characters(100, 5000))
            .autorias(List.of(faker.lorem().characters(3, 100)))
            .fontes(List.of(faker.lorem().characters(3, 250)));
    }
}

