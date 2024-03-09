package microservice.microtorneios.application.core.domain;

import lombok.ToString;

import java.time.Year;
import java.util.Set;

@ToString
public final class Torneio {

    private Long id;

    private String nome;

    private Year ano;

    private Set<Time> times;

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

    public Set<Time> getTimes() {
        return times;
    }

    public void setTimes(Set<Time> times) {
        this.times = times;
    }

}

