package microservice.microinscricoes.application.core.domain.enums;

public enum ETipoPagamento {

    DEBITO("Débito"),
    CREDITO("Crédito");

    private final String valor;

    ETipoPagamento(String valor) {
        this.valor = valor;
    }

    String getValor() {
        return this.valor;
    }
}

