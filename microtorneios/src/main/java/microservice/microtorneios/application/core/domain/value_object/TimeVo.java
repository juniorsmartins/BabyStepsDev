package microservice.microtorneios.application.core.domain.value_object;

import java.util.Objects;

public final class TimeVo {

    private Long id;

    public TimeVo() { }

    public TimeVo(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeVo time = (TimeVo) o;
        return Objects.equals(getId(), time.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

