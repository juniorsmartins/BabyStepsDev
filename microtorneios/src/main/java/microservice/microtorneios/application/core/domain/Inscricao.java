package microservice.microtorneios.application.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Inscricao {

    private Long id;

    private Long torneioId;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTorneioId() {
        return torneioId;
    }

    public void setTorneioId(Long torneioId) {
        this.torneioId = torneioId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}

