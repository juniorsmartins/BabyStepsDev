package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.usecase.regras.RuleNomenclaturaUnicaDeEditoria;
import microservice.micronoticias.application.core.usecase.regras.RuleNoticiaUnica;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BusinessRulesConfig {

    @Bean
    public RuleNomenclaturaUnicaDeEditoria ruleNomenclaturaUnicaDeEditoria(EditoriaRepository editoriaRepository) {
        return new RuleNomenclaturaUnicaDeEditoria(editoriaRepository);
    }

    @Bean
    public RuleNoticiaUnica ruleNoticiaUnica(NoticiaRepository noticiaRepository) {
        return new RuleNoticiaUnica(noticiaRepository);
    }
}

