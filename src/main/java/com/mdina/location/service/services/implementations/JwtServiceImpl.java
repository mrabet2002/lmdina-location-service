package com.mdina.location.service.services.implementations;

import com.mdina.location.config.JwtConfig;
import com.mdina.location.enumerations.Role;
import com.mdina.location.service.dto.User;
import com.mdina.location.service.services.interfaces.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * Ce service va nous faciliter l'utilisation de la bibliothèque JJWT
 * en implémentant ses different fonctionnalités dans des méthodes simple à utiliser.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements IJwtService {

    private final JwtConfig jwtConfig;

    @Override
    public String getUsername(String token) {
        return getAccessTokenClaims(token).getSubject();
    }

    @Override
    public Long getUserId(String token) {
        return Long.parseLong(getAccessTokenClaims(token).get("id").toString());
    }

    @Override
    public Role getUserRole(String token) {
        return Role.valueOf(getAccessTokenClaims(token).get("role").toString());
    }

    @Override
    public User getUserFromToken(String jwt) {
        User user = new User();
        Claims claims = getAccessTokenClaims(jwt);
        user.setId(Long.parseLong(claims.get("id").toString()));
        user.setEmail(claims.getSubject());
        user.setRole(Role.valueOf(claims.get("role").toString()));
        return user;
    }

    /**
     * Extraire le "username" de l'utilisateur authentifier du contexte "SecurityContextHolder"
     */
    @Override
    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken){
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    // Helper method to get access token claims
    private Claims getAccessTokenClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getAccessTokenSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
