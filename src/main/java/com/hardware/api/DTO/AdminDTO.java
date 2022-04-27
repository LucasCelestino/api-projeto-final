package com.hardware.api.DTO;

import javax.validation.constraints.NotBlank;

import com.hardware.api.Model.Admin;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminDTO
{
    private Long id;

    @NotBlank
    @Length(min = 1, max = 60)
    private String name;

    @Length(min = 10, max = 60)
    private String email;

    @NotBlank
    @Length(min = 10, max = 60)
    private String password;

    public Admin toObject()
    {
        return new Admin(this.id, this.name, this.email, this.password);
    }
}
