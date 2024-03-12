package microservice.microtorneios.application.core.domain;

import java.time.Year;
import java.util.Objects;
import java.util.Set;

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

    @Override
    public String toString() {
        return "Torneio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ano=" + ano +
                ", times=" + times +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Torneio torneio = (Torneio) o;
        return Objects.equals(getId(), torneio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

