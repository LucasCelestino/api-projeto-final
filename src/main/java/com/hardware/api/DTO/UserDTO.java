package com.hardware.api.DTO;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO
{
    private Long id;

    @NotBlank
    @Length(min = 1, max = 60)
    private String nome;

    @Length(min = 10, max = 60)
    private String email;

    @NotBlank
    @Length(min = 10, max = 60)
    private String password;

    private String isAdmin;
}
