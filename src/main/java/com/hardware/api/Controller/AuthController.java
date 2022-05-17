package com.hardware.api.Controller;

import javax.servlet.http.HttpServletResponse;

import com.hardware.api.Security.JWTUtil;
import com.hardware.api.Security.UserDetailsImpl;
import com.hardware.api.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/refresh_token") public ResponseEntity<Void> refreshToken(HttpServletResponse response)
    {
        UserDetailsImpl user =  UserService.authenticated();

        if (user != null)
        {
            String token = jwtUtil.generateToken(user.getUsername());

            response.addHeader("Authorization", "Bearer " + token);
            
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
