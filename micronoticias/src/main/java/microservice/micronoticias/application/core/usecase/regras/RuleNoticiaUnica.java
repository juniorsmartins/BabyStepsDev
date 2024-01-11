package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.application.core.domain.Noticia;

public class RuleNoticiaUnica implements RuleChainOfResponsibility {

    private RuleChainOfResponsibility nextRule;

    public RuleNoticiaUnica(RuleChainOfResponsibility ruleChainOfResponsibility) {
        this.nextRule = ruleChainOfResponsibility;
    }

    @Override
    public Noticia execute(Noticia noticia) {
        // TODO add regra
        System.out.println("\n\nRegra de Notícia\n");

        if (nextRule != null) {
            this.nextRule.execute(noticia);
        }

        return noticia;
    }
}
