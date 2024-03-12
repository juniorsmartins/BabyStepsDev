package microservice.microinscricoes.application.core.domain;

public final class Torneio {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Torneio{" +
                "id=" + id +
                '}';
    }

}

