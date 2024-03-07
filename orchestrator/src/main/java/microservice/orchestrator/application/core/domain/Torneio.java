package microservice.orchestrator.application.core.domain;

import java.time.Year;

public final class Torneio {

    private Long id;

    private String nome;

    private Year ano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Year getAno() {
        return ano;
    }

    public void setAno(Year ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Torneio{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", ano=" + ano +
            '}';
    }
}

