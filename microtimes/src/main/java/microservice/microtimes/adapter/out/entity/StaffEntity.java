package microservice.microtimes.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public final class StaffEntity implements Serializable {

    @Column(name = "presidente", table = "time_staff")
    private String presidente;

    @Column(name = "vice_presidente", table = "time_staff")
    private String vicePresidente;

    @Column(name = "head_coach", table = "time_staff")
    private String headCoach;
}

