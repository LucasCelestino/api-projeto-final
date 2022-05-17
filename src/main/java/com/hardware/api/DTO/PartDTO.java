package com.hardware.api.DTO;

import com.hardware.api.Model.Brand;

import lombok.Getter;

// import javax.validation.constraints.NotBlank;

// import com.hardware.api.Model.Admin;

// import org.hibernate.validator.constraints.Length;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartDTO
{
    private Long id;

    private String name;

    private Double price;
    
    private String url;

    private Brand brand;
}

