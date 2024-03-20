package com.baltinfo.radius.db.constants;

import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * Тип субъекта
 * </p>
 *
 * @author Lapenok Igor
 * @since 17.08.2018
 */
public enum TypeSubjectConstant {
    FL(1L, 1L, "Физическое лицо"),
    YL(0L, 2L, "Юридическое лицо"),
    IP(2L, 3L, "Индивидуальный предприниматель");

    private final Long finallId;
    private final Long radiusId;
    private final String name;

    TypeSubjectConstant(Long finallId, Long radiusId, String name) {
        this.finallId = finallId;
        this.radiusId = radiusId;
        this.name = name;
    }

    public Long getFinallId() {
        return finallId;
    }

    public Long getRadiusId() {
        return radiusId;
    }

    public String getName() {
        return name;
    }

    public static Optional<TypeSubjectConstant> getByName(String name) {
        return Arrays.stream(values()).filter(x -> x.name.equals(name)).findFirst();
    }
}
