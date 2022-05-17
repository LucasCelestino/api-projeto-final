package com.hardware.api.Security;


import java.util.Date;

import com.hardware.api.Model.PerfilType;
import com.hardware.api.Service.UserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil
{
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username)
    {
        return Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(
        System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS512,
        secret.getBytes())
        .compact();
    }

    public boolean tokenValido(String token)
    {
        Claims claims = this.getClaims(token);
        if (claims != null)
        {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (username != null && expirationDate != null && now.before(expirationDate)) 
            {
                return true;
            }
        }

        return false;
    }

    public String getUsername(String token)
    {
        Claims claims = getClaims(token);

        if (claims != null)
        {
            return claims.getSubject();
        }
        
        return null;
    }

    private Claims getClaims(String token)
    {
        try
        {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }

        catch (Exception e)
        {
            return null;
        }
    }

    public boolean authorized(Long id)
    {
        UserDetailsImpl user = UserService.authenticated();

        if (user == null || (!user.hasRole(PerfilType.ADMIN)
        && !id.equals(user.getId()))) {
        return false;
        }
        return true;
    }
}
