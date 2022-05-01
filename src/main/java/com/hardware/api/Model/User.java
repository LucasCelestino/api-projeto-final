package com.hardware.api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    @Column(name = "password", length = 90)
    private String password;

    @Column(name = "phone", length = 75)
    private String phone;

    @Column(name = "url", length = 200)
    private String url;
}
