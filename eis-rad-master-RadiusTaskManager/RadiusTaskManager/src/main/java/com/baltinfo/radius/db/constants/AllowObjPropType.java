package com.baltinfo.radius.db.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Тип допустимого свойство объекта
 * </p>
 *
 * @author Sergeev Yuriy
 * @since 08.11.2019
 */
public enum AllowObjPropType {

    STRING(1, "STRING"),
    DATE(2, "DATE"),
    LIST(3, "LIST"),
    NUMBER(4, "NUMBER"),
    BOOLEAN(5, "BOOLEAN");

    private final int id;
    private final String name;

    AllowObjPropType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<String> getAllNames() {
        return Arrays.stream( AllowObjPropType.values())
                .map(AllowObjPropType::getName)
                .collect(Collectors.toList());
    }

    public static AllowObjPropType getByName(String name) {
        return Arrays.stream(AllowObjPropType.values())
                .filter(type -> type.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isString() {
        return this.equals(STRING);
    }

    public boolean isList() {
        return this.equals(LIST);
    }

    public boolean isNumber() {
        return this.equals(NUMBER);
    }

    public boolean isBool() {
        return this.equals(BOOLEAN);
    }

    public boolean isDate() {
        return this.equals(DATE);
    }
}