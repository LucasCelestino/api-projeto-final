package com.hardware.api.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity
{
    @Column(name = "name", length = 75)
    private String name;

    @Column(name = "email", length = 75)
    private String email;

    @Column(name = "phone", length = 75)
    private String phone;

    @Column(name = "url", length = 75)
    private String url;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profile")
    private Set<Integer> profiles = new HashSet<>();

    public Set<PerfilType> getProfiles()
    {
        return profiles.stream()
        .map(x -> PerfilType.toEnum(x))
        .collect(Collectors.toSet());
    }

    public Set<Integer> getProfilesAsInteger()
    {
        return profiles;
    }

    public void addProfile(PerfilType profile)
    {
        this.profiles.add(profile.getCod());
    }
    
    @Column(name = "nm_login", length = 80, unique = true)
    private String login;

    @Column(name = "nm_password")
    @Getter(onMethod = @__(@JsonIgnore))
    @Setter(onMethod = @__(@JsonProperty))
    private String password;
}
