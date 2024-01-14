package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.usecase.regras.RuleNomenclaturaUnicaDeEditoriaToCreateNews;
import microservice.micronoticias.application.core.usecase.regras.RuleNoticiaUnicaToCreateNews;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BusinessRulesConfig {

    @Bean
    public RuleNomenclaturaUnicaDeEditoriaToCreateNews ruleNomenclaturaUnicaDeEditoria(EditoriaRepository editoriaRepository) {
        return new RuleNomenclaturaUnicaDeEditoriaToCreateNews(editoriaRepository);
    }

    @Bean
    public RuleNoticiaUnicaToCreateNews ruleNoticiaUnica(NoticiaRepository noticiaRepository) {
        return new RuleNoticiaUnicaToCreateNews(noticiaRepository);
    }
}

