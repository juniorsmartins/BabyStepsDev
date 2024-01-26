package microservice.microtimes.adapter.out.entity;

import microservice.microtimes.utility.FactoryObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit Entity - Time")
class TimeEntityUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Test
    @DisplayName("ids diferentes")
    void dadoTimesEntityValidosComIdsDiferentes_QuandoUsarEquals_RetornarFalse() {
        var timesIguais =
        var noticiasIguais = noticiaEntity1.equals(noticiaEntity2);
        Assertions.assertFalse(timesIguais);
    }
}

