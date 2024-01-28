package microservice.microtimes.application.core.domain.enums;

import java.util.Arrays;

public enum ActivityStatusEnum {

    ATIVO("ATIVO"),

    INATIVO("INATIVO");

    private final String status;

    ActivityStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static ActivityStatusEnum toEnum(String status) {
        if (status == null) return null;

        return Arrays.stream(ActivityStatusEnum.values())
            .filter(activity -> status.equals(activity.getStatus()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(String.format("ActivityStatus inválido: %s", status)));
    }
}

