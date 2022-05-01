package com.hardware.api.Mapper;

import java.util.ArrayList;
import java.util.List;

import com.hardware.api.DTO.UserDTO;
import com.hardware.api.Model.User;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class UserMapper 
{
    public User toEntity(UserDTO userDTO)
    {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setUrl(userDTO.getUrl());

        return user;
    }

    public UserDTO toDTO(User user)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhone(user.getPhone());
        userDTO.setUrl(user.getUrl());

        return userDTO;
    }

    public List<UserDTO> toDTO(List<User> list)
    {
		List<UserDTO> lista = new ArrayList<>();

		for (User p : list) {
			lista.add(this.toDTO(p));
		}

		return lista;
	}
}
