package com.hardware.api.Service;

import com.hardware.api.Model.User;
import com.hardware.api.Repository.UserRepository;
import com.hardware.api.Security.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByLogin(username);

        if (user == null)
        {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user.getId(),user.getLogin(), user.getPassword(),user.getProfiles());
    }
    
}
