package microservice.microinscricoes.utility;

import microservice.microinscricoes.adapter.in.dto.request.InscricaoOpenDtoIn;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.util.Set;

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
//            .dataInicio("01/01/2024")
//            .dataFim("01/02/2024")
            .valor(BigDecimal.valueOf(50));
    }

}

