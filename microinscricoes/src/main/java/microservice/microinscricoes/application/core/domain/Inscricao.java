package microservice.microinscricoes.application.core.domain;

import microservice.microinscricoes.application.core.domain.enums.EInscricaoStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Inscricao {

    private Long id;

    private Torneio torneio;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private BigDecimal valor;

    private EInscricaoStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Torneio getTorneio() {
        return torneio;
    }

    public void setTorneio(Torneio torneio) {
        this.torneio = torneio;
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

    public EInscricaoStatus getStatus() {
        return status;
    }

    public void setStatus(EInscricaoStatus status) {
        this.status = status;
    }
}

