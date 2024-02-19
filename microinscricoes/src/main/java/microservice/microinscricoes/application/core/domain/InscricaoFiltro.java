package microservice.microinscricoes.application.core.domain;

public final class InscricaoFiltro {

    private String id;

    private String torneioId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTorneioId() {
        return torneioId;
    }

    public void setTorneioId(String torneioId) {
        this.torneioId = torneioId;
    }

    @Override
    public String toString() {
        return "InscricaoFiltro{" +
            "id='" + id + '\'' +
            ", torneioId='" + torneioId + '\'' +
            '}';
    }
}

