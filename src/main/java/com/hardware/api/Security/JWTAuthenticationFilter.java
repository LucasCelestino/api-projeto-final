package com.hardware.api.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardware.api.DTO.CredentialsDTO;
import com.hardware.api.Model.User;
import com.hardware.api.Repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;
    private UserRepository userRepository;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserRepository userRepository)
    {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        try
        {
            CredentialsDTO creds = new ObjectMapper()
            .readValue(request.getInputStream(), CredentialsDTO.class);
            UsernamePasswordAuthenticationToken authToken = new
            UsernamePasswordAuthenticationToken(creds.getLogin(),
            creds.getPassword(), new ArrayList<>());
            Authentication auth =
            authenticationManager.authenticate(authToken);
            return auth;
        }
        catch (IOException e)
        { 
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException 
    {
        String username = ((UserDetailsImpl) authResult.getPrincipal())
        .getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authentication", "Bearer " + token);
        response.addHeader("access-control-expose-headers","Authorization");
        User user = userRepository.findByLogin(username);
        response.setContentType("application/json");
        response.getWriter().append(this.jsonAuth(token, user));
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws java.io.IOException, javax.servlet.ServletException
    {
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(jsonError());
    }

    private String jsonAuth(String token, User user)
    {
        return "{\"token\": \"" + token + "\"" + ", " +
        "\"username\": \"" + user.getName() + "\", " +
        "\"profile\": " + user.getProfiles().stream()
        .map(x -> "\"" + x + "\"")
        .collect(Collectors.toList()) + "}";
    }

    private String jsonError()
    {
    return "{\"timestamp\": " + new Date().getTime() + ", "
    + "\"status\": 401, " + "\"error\": \"Não autorizado\", "
    + "\"message\": \"Email ou senha inválidos\", "
    + "\"path\": \"/login\"}";
    }
}
