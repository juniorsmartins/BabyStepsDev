package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.out.EditoriaBuscarPorNomenclaturaAdapter;
import microservice.micronoticias.application.core.usecase.regras.RuleNomenclaturaUnicaDeEditoriaToCreateNews;
import microservice.micronoticias.application.core.usecase.regras.RuleNoticiaUnica;
import microservice.micronoticias.application.core.usecase.regras.SingleNamingRuleForEditorial;
import microservice.micronoticias.application.port.output.EditoriaBuscarPorNomenclaturaOutputPort;
import microservice.micronoticias.application.port.output.NoticiaBuscarPorTituloOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BusinessRulesConfig {

    @Bean
    public RuleNomenclaturaUnicaDeEditoriaToCreateNews ruleNomenclaturaUnicaDeEditoria(EditoriaBuscarPorNomenclaturaOutputPort editoriaBuscarPorNomenclaturaOutputPort) {
        return new RuleNomenclaturaUnicaDeEditoriaToCreateNews(editoriaBuscarPorNomenclaturaOutputPort);
    }

    @Bean
    public RuleNoticiaUnica ruleNoticiaUnica(NoticiaBuscarPorTituloOutputPort noticiaBuscarPorTituloOutputPort) {
        return new RuleNoticiaUnica(noticiaBuscarPorTituloOutputPort);
    }

    @Bean
    public SingleNamingRuleForEditorial singleNamingRuleForEditorial(EditoriaBuscarPorNomenclaturaAdapter editoriaBuscarPorNomenclaturaAdapter) {
        return new SingleNamingRuleForEditorial(editoriaBuscarPorNomenclaturaAdapter);
    }
}

