package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;

import com.hardware.api.DTO.UserDTO;
import com.hardware.api.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements ServiceInterface<UserDTO>
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAll()
    {
        return userRepository.findAll();
    }

    @Override
    public UserDTO findById(Long id)
    {
        Optional<UserDTO> user = userRepository.findById(id);

        if(user.isPresent())
        {
            return user.get();
        }

        return null;
    }

    @Override
    public UserDTO create(UserDTO user)
    {
        UserDTO newUser = userRepository.save(user);

        return newUser;
    }

    @Override
    public boolean update(UserDTO obj) 
    {
        Optional<UserDTO> user = userRepository.findById(obj.getId());

        if(user.isPresent())
        {
            UserDTO userDTO = user.get();

            userDTO.setNome(obj.getNome());
            userDTO.setEmail(obj.getEmail());
            userDTO.setPassword(obj.getPassword());
            userDTO.setIsAdmin(obj.getIsAdmin());

            userRepository.save(userDTO);

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Long id)
    {
        if(userRepository.existsById(id))
        {
            userRepository.deleteById(id);

            return  true;
        }

        return false;
    }
    
}
