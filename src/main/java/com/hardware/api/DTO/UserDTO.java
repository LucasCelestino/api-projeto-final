package com.hardware.api.DTO;

import javax.validation.constraints.Email;

import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO
{
    private Long id;

    private String name;

    @Email(message = "Email precisa ser um formato válido")
    private String email;
    
    private String password;

    private String phone;

    private String url;
}