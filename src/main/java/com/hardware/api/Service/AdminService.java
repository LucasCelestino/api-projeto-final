package com.hardware.api.Service;

import java.util.Optional;

import com.hardware.api.DTO.AdminDTO;
import com.hardware.api.Model.Admin;
import com.hardware.api.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements ServiceInterface<AdminDTO>
{

    @Autowired
    private UserRepository userRepository;

    // @Override
    // public List<User> findAll()
    // {
    //     return userRepository.findAll();
    // }

    // @Override
    public AdminDTO findById(Long id)
    {
        Optional<Admin> OptionalAdmin = userRepository.findById(id);

        if(OptionalAdmin.isPresent())
        {
            Admin admin = OptionalAdmin.get();

            AdminDTO adminDTO = new AdminDTO();

            adminDTO.setId(admin.getId());
            adminDTO.setName(admin.getName());
            adminDTO.setEmail(admin.getEmail());
            adminDTO.setPassword(admin.getPassword());

            return adminDTO;
        }

        return null;
    }

    // @Override
    // public User create(User objUser)
    // {
    //     User newUser = userRepository.save(objUser);

    //     return newUser;
    // }

    // @Override
    // public boolean update(User objUser) 
    // {
    //     Optional<User> user = userRepository.findById(objUser.getId());

    //     if(user.isPresent())
    //     {
    //         User userUpdate = user.get();

    //         userUpdate.setName(objUser.getName());
    //         userUpdate.setEmail(objUser.getEmail());
    //         userUpdate.setPassword(objUser.getPassword());
    //         userUpdate.setIsAdmin(objUser.getIsAdmin());

    //         userRepository.save(userUpdate);

    //         return true;
    //     }

    //     return false;
    // }

    // @Override
    // public boolean delete(Long id)
    // {
    //     if(userRepository.existsById(id))
    //     {
    //         userRepository.deleteById(id);

    //         return  true;
    //     }

    //     return false;
    // }
    
}
