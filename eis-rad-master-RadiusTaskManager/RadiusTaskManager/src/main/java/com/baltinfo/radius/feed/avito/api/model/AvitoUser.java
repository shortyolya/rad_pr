package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
public class AvitoUser {
    @JsonProperty("id")
    private final Long id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("email")
    private final String email;
    @JsonProperty("phone")
    private final Long phone;
    @JsonProperty("profile_url")
    private final String profileUrl;

    @JsonCreator
    public AvitoUser(@JsonProperty("id") Long id,
                     @JsonProperty("name") String name,
                     @JsonProperty("email") String email,
                     @JsonProperty("phone") Long phone,
                     @JsonProperty("profile_url") String profileUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profileUrl = profileUrl;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("phone")
    public Long getPhone() {
        return phone;
    }

    @JsonProperty("profileUrl")
    public String getProfileUrl() {
        return profileUrl;
    }

    @Override
    public String toString() {
        return "AvitoUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }
}
