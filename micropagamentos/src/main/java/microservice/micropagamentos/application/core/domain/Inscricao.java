package microservice.micropagamentos.application.core.domain;

import java.util.Objects;

public final class Inscricao {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Inscricao{" +
            "id=" + id +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscricao inscricao = (Inscricao) o;
        return Objects.equals(getId(), inscricao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}


