package microservice.microtorneios.application.core.domain;

import java.time.Year;
import java.util.Set;

public final class Torneio {

    private String nome;

    private Year ano;

    private Set<TimeInventory> times;

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

    public Set<TimeInventory> getTimes() {
        return times;
    }

    public void setTimes(Set<TimeInventory> times) {
        this.times = times;
    }
}

