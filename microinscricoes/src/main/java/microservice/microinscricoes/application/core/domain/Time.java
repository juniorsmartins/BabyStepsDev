package microservice.microinscricoes.application.core.domain;

public final class Time {

    private Long id;

    private String nomeFantasia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public String toString() {
        return "Time{" +
            "id=" + id +
            ", nomeFantasia='" + nomeFantasia + '\'' +
            '}';
    }
}

