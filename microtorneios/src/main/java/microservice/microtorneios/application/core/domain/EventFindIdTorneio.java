package microservice.microtorneios.application.core.domain;

public final class EventFindIdTorneio {

    private Long inscricaoId;

    private Long torneioId;

    public Long getInscricaoId() {
        return inscricaoId;
    }

    public void setInscricaoId(Long inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public Long getTorneioId() {
        return torneioId;
    }

    public void setTorneioId(Long torneioId) {
        this.torneioId = torneioId;
    }
}

