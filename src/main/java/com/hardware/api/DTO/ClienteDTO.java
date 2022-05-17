package com.hardware.api.DTO;

import lombok.Getter;

// import javax.validation.constraints.NotBlank;

// import com.hardware.api.Model.Admin;

// import org.hibernate.validator.constraints.Length;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO
{
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private String url;
}