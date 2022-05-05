package com.hardware.api.DTO;

import com.hardware.api.Model.Part;
import com.hardware.api.Model.User;

import lombok.Getter;

// import javax.validation.constraints.NotBlank;

// import com.hardware.api.Model.Admin;

// import org.hibernate.validator.constraints.Length;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetDTO
{
    private Long id;

    private User user;

    private Part part;
}
