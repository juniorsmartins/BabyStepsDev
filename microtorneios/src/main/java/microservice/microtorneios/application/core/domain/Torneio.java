package microservice.microtorneios.application.core.domain;

import java.time.Year;

public final class Torneio {

    private String nome;

    private Year ano;

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
}

