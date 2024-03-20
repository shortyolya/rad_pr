package com.baltinfo.radius.db.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 14.11.2020
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Value")
public class Value {

    @JsonProperty("propertyName")
    private String propertyName;
    @JsonProperty("propertyCode")
    private String propertyCode;
    @JsonProperty("parentValueCode")
    private String parentValueCode;
    @JsonProperty("valueCode")
    private String valueCode;
    @JsonProperty("value")
    private String value;

    @JsonCreator
    private Value(@JsonProperty("propertyName") String propertyName,
                  @JsonProperty("propertyCode") String propertyCode,
                  @JsonProperty("parentValueCode") String parentValueCode,
                  @JsonProperty("valueCode") String valueCode,
                  @JsonProperty("value") String value) {
        this.propertyName = propertyName;
        this.propertyCode = propertyCode;
        this.parentValueCode = parentValueCode;
        this.valueCode = valueCode;
        this.value = value;
    }

    public static ValueBuilder builder() {
        return new ValueBuilder();
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getParentValueCode() {
        return parentValueCode;
    }

    public void setParentValueCode(String parentValueCode) {
        this.parentValueCode = parentValueCode;
    }

    public String getValueCode() {
        return valueCode;
    }

    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static final class ValueBuilder {
        public String propertyName;
        public String propertyCode;
        public String parentValueCode;
        public String valueCode;
        public String value;

        private ValueBuilder() {
        }

        public ValueBuilder withPropertyName(String propertyName) {
            this.propertyName = propertyName;
            return this;
        }

        public ValueBuilder withPropertyCode(String propertyCode) {
            this.propertyCode = propertyCode;
            return this;
        }

        public ValueBuilder withParentValueCode(String parentValueCode) {
            this.parentValueCode = parentValueCode;
            return this;
        }

        public ValueBuilder withValueCode(String valueCode) {
            this.valueCode = valueCode;
            return this;
        }

        public ValueBuilder withValue(String value) {
            this.value = value;
            return this;
        }

        public Value build() {
            return new Value(propertyName, propertyCode, parentValueCode, valueCode, value);
        }
    }
}
