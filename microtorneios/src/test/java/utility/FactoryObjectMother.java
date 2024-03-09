package utility;

import microservice.microtorneios.adapters.in.controller.dto.request.TorneioCreateDtoRequest;
import net.datafaker.Faker;

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

    // Padrão Builder
    public TorneioCreateDtoRequest.TorneioCreateDtoRequestBuilder gerarTorneioCreateDtoRequestBuilder() {

        return TorneioCreateDtoRequest.builder()
            .nome(faker.basketball().coaches())
            .ano(faker.number().numberBetween(1900, 2024));
    }
}

