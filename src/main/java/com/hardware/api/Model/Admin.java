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
@Table(name = "admins")
public class Admin extends AbstractEntity
{
    @Column(name = "name", length = 75)
    private String name;

    @Column(name = "email", length = 75)
    private String email;

    @Column(name = "password", length = 90)
    private String password;
}
