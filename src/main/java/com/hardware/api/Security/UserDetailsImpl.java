package com.hardware.api.Security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.hardware.api.Model.PerfilType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl() { }

    public UserDetailsImpl(Long id, String login, String password, Set<PerfilType> profiles)
    {
            super();
            this.id = id;
            this.login = login;
            this.password = password;
            this.authorities = profiles.stream()
            .map(x -> new SimpleGrantedAuthority(x.getDescricao()))
            .collect(Collectors.toList());
    }
    
    public Long getId() { return id; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.authorities;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
    
}
