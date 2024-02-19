package microservice.microinscricoes.utility;

import microservice.microinscricoes.adapter.in.dto.request.InscricaoOpenDtoIn;
import microservice.microinscricoes.adapter.out.repository.entity.InscricaoEntity;
import microservice.microinscricoes.adapter.out.repository.entity.TorneioEntity;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.Torneio;
import microservice.microinscricoes.application.core.domain.enums.EInscricaoStatus;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

// Padrão Object Mother
public final class FactoryObjectMother {

    private static FactoryObjectMother singletonFactory;

    private final Faker faker = new Faker();

    private FactoryObjectMother() { }

    // Padrão Singleton
    public static synchronized FactoryObjectMother singleton() {
        if (singletonFactory == null) {
            singletonFactory = new FactoryObjectMother();
        }
        return singletonFactory;
    }

    // Padrão Builder
    public InscricaoOpenDtoIn.InscricaoOpenDtoInBuilder gerarInscricaoOpenDtoInBuilder() {

        return InscricaoOpenDtoIn.builder()
            .dataInicio("10/10/2023")
            .dataFim("10/11/2023")
            .valor(BigDecimal.valueOf(100));
    }

    // Padrão JavaBeans
    public Inscricao gerarInscricao() {

        var torneio = this.gerarTorneio();
        torneio.setId(1L);

        var ano = faker.number().numberBetween(1900, 2024);
        var mes = faker.number().numberBetween(1, 12);
        var dia = faker.number().numberBetween(1, 28);

        var inscricao = new Inscricao();
        inscricao.setTorneio(torneio);
        inscricao.setDataInicio(LocalDate.of(ano, mes, dia));
        inscricao.setDataFim(LocalDate.of(ano, mes, dia));
        inscricao.setValor(BigDecimal.valueOf(100));

        return inscricao;
    }

    public InscricaoEntity.InscricaoEntityBuilder gerarInscricaoEntityBuilder() {

        var ano = faker.number().numberBetween(1900, 2024);
        var mes = faker.number().numberBetween(1, 12);
        var dia = faker.number().numberBetween(1, 28);

        return InscricaoEntity.builder()
            .dataInicio(LocalDate.of(ano, mes, dia))
            .dataFim(LocalDate.of(ano, mes, dia))
            .valor(BigDecimal.valueOf(100))
            .status(EInscricaoStatus.ATIVO);
    }

    // Padrão JavaBeans
    public Torneio gerarTorneio() {

        var torneio = new Torneio();
        torneio.setNome(faker.lorem().characters(10, 30));
        torneio.setAno(Year.of(faker.number().numberBetween(1900, 2024)));

        return torneio;
    }

    // Padrão Builder
    public TorneioEntity.TorneioEntityBuilder gerarTorneioEntityBuilder() {

        return TorneioEntity.builder()
            .nome(faker.lorem().characters(10, 30))
            .ano(Year.of(faker.number().numberBetween(1900, 2024)));
    }

}

