package microservice.microtimes.utility;

import microservice.microtimes.adapter.in.controller.dto.request.TimeCreateDtoRequest;
import net.datafaker.Faker;

import java.time.LocalDate;

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
    public TimeCreateDtoRequest.TimeCreateDtoRequestBuilder gerarTimeCreateDtoRequestBuilder() {

        return TimeCreateDtoRequest.builder()
            .nomeFantasia(faker.lorem().characters(10, 20))
            .razaoSocial(faker.lorem().characters(10, 20))
            .cnpj(faker.cnpj().valid())
            .estado(faker.lorem().characters(2, 2))
            .cidade(faker.lorem().characters(10, 20))
            .data(LocalDate.now())
            .descricao(faker.lorem().characters(20, 40))
            .presidente(faker.hobbit().thorinsCompany())
            .vicePresidente(faker.worldOfWarcraft().hero())
            .headCoach(faker.superhero().name());
    }
}

