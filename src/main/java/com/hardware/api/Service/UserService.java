package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;

import com.hardware.api.DTO.UserDTO;
import com.hardware.api.Mapper.UserMapper;
import com.hardware.api.Model.User;
import com.hardware.api.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ServiceInterface<UserDTO>
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    // @Override
    public List<UserDTO> findAll()
    {
        return userMapper.toDTO(userRepository.findAll());
    }

    // @Override
    public UserDTO findById(Long id)
    {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent())
        {
            return userMapper.toDTO(optionalUser.get());
        }

        return null;
    }

    // @Override
    public UserDTO create(UserDTO userDTO)
    {
        User newUser = userRepository.save(userMapper.toEntity(userDTO));

        return userMapper.toDTO(newUser);
    }

    // @Override
    public boolean update(UserDTO userDTO) 
    {
        if(userRepository.existsById(userDTO.getId()))
        {
            userRepository.save(userMapper.toEntity(userDTO));

            return true;
        }

        return false;
    }

    // @Override
    public boolean delete(Long id)
    {
        if(userRepository.existsById(id))
        {
            userRepository.deleteById(id);
            
            return true;
        }

        return false;
    }
    
}
