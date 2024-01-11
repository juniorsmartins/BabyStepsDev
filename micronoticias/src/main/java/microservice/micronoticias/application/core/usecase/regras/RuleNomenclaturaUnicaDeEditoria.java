package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.application.core.domain.Noticia;

public class RuleNomenclaturaUnicaDeEditoria implements RuleChainOfResponsibility {

    private RuleChainOfResponsibility nextRule;

    public RuleNomenclaturaUnicaDeEditoria(RuleChainOfResponsibility ruleChainOfResponsibility) {
        this.nextRule = ruleChainOfResponsibility;
    }

    @Override
    public Noticia runRule(Noticia noticia) {
        // TODO add regra
        System.out.println("\n\nRegra de Nomenclatura\n");

        if (nextRule != null) {
            this.nextRule.runRule(noticia);
        }

        return noticia;
    }
}

