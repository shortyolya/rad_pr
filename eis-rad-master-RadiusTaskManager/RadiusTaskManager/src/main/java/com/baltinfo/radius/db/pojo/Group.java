package com.baltinfo.radius.db.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@JsonRootName(value = "Group")
public class Group {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("values")
    private List<Value> values;

    @JsonCreator
    private Group(@JsonProperty("id") Long id,
                  @JsonProperty("name") String name,
                  @JsonProperty("values") List<Value> values) {
        this.id = id;
        this.name = name;
        this.values = values;
    }

    public static GroupBuilder builder() {
        return new GroupBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }


    public static final class GroupBuilder {
        public Long id;
        public String name;
        public List<Value> values;

        private GroupBuilder() {
        }

        public GroupBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public GroupBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GroupBuilder withValues(List<Value> values) {
            this.values = values;
            return this;
        }

        public Group build() {
            return new Group(id, name, values);
        }
    }
}
