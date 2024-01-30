package microservice.microtorneios.utility;

import microservice.microtorneios.adapters.in.dto.request.TorneioCreateDtoRequest;
import microservice.microtorneios.adapters.out.repository.entity.TimeInventoryEntity;
import microservice.microtorneios.adapters.out.repository.entity.TorneioEntity;
import microservice.microtorneios.application.core.domain.enums.ActivityStatusEnum;
import net.datafaker.Faker;

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

    public TimeInventoryEntity.TimeInventoryEntityBuilder gerarTimeInventoryEntityBuilder() {

        return TimeInventoryEntity.builder()
            .nomeFantasia(faker.lorem().characters(20))
            .estado(faker.lorem().characters(2))
            .status(ActivityStatusEnum.ATIVO);
    }

    public TorneioCreateDtoRequest.TorneioCreateDtoRequestBuilder gerarTorneioCreateDtoRequest() {

        return TorneioCreateDtoRequest.builder()
            .nome(faker.lorem().characters(20))
            .ano(2000);
    }

    public TorneioEntity.TorneioEntityBuilder gerarTorneioEntityBuilder() {

        return TorneioEntity.builder()
            .nome(faker.lorem().characters(20))
            .ano(Year.of(1990));
    }
}

