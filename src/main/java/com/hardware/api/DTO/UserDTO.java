package com.hardware.api.DTO;

import java.util.Set;

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

    private String email;

    private String phone;

    private String url;

    private Set<Integer> profiles;

    private String login;

    private String password;
}