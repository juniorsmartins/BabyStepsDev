package microservice.microtimes.application.core.domain.value_object;

import java.util.Objects;

public final class TorneioVo {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TorneioVo() { }

    public TorneioVo(final Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TorneioVo{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TorneioVo torneioVo = (TorneioVo) o;
        return Objects.equals(getId(), torneioVo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

