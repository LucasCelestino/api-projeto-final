package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;

import com.hardware.api.DTO.UserDTO;
import com.hardware.api.Exception.AuthorizationException;
import com.hardware.api.Mapper.UserMapper;
import com.hardware.api.Model.User;
import com.hardware.api.Repository.UserRepository;
import com.hardware.api.Security.JWTUtil;
import com.hardware.api.Security.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ServiceInterface<UserDTO>
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JWTUtil jwtUtil;

    // @Override
    public List<UserDTO> findAll()
    {
        return userMapper.toDTO(userRepository.findAll());
    }

    //@Override
    public UserDTO findById(Long id) throws AuthorizationException 
    {
        if (!jwtUtil.authorized(id))
        {
            throw new AuthorizationException("Acesso negado!");
        }

        Optional<User> obj = userRepository.findById(id);

        if (obj.isPresent())
        {
            return userMapper.toDTO(obj.get());
        }
        
        return null;
    }
    
    // @Override
    public UserDTO create(UserDTO userDTO)
    {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
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

    public static UserDetailsImpl authenticated()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null)
        {
            return (UserDetailsImpl) auth.getPrincipal();
        }
        
        return null;
    }
}
