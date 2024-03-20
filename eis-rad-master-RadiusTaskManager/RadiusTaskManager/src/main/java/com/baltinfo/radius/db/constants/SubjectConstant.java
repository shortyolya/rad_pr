package com.baltinfo.radius.db.constants;

import java.util.Arrays;

/**
 * @author Igor Lapenok
 * @since 28.11.2022
 */
public enum SubjectConstant {
    RAD(1L, "", "web.seq_obj_code"),
    RAD_HOLDING(47392L, "РХ-", "web.seq_obj_code_holding");

    private final Long id;
    private final String prefix;
    private final String seqName;

    SubjectConstant(Long id, String prefix, String seqName) {
        this.id = id;
        this.prefix = prefix;
        this.seqName = seqName;
    }

    public static SubjectConstant getById(Long subUnid) {
        return Arrays.stream(values())
                .filter(sub -> sub.id.equals(subUnid))
                .findFirst()
                .orElse(null);
    }

    public Long getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSeqName() {
        return seqName;
    }
}
