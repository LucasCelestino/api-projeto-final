package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;

import com.hardware.api.Model.User;
import com.hardware.api.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ServiceInterface<User>
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id)
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent())
        {
            return user.get();
        }

        return null;
    }

    @Override
    public User create(User objUser)
    {
        User newUser = userRepository.save(objUser);

        return newUser;
    }

    @Override
    public boolean update(User objUser) 
    {
        Optional<User> user = userRepository.findById(objUser.getId());

        if(user.isPresent())
        {
            User userUpdate = user.get();

            userUpdate.setName(objUser.getName());
            userUpdate.setEmail(objUser.getEmail());
            userUpdate.setPassword(objUser.getPassword());
            userUpdate.setIsAdmin(objUser.getIsAdmin());

            userRepository.save(userUpdate);

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
