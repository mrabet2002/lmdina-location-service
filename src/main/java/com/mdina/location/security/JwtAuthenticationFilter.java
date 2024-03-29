package com.mdina.location.security;

import com.mdina.location.service.dto.User;
import com.mdina.location.service.mappers.JsonMapper;
import com.mdina.location.service.services.interfaces.IJwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthenticationFilter is the first thing that will receive the request
 * It extends {@link OncePerRequestFilter}
 * witch is a filter for every coming request
 * So ore filter will be active for every request send by the client
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final IJwtService jwtService;
    private final JsonMapper jsonMapper;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Getting the token from the received request
        final String authHeader = request.getHeader("Authorization");
        // Checking if "Authorization" header contains a bearer token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Chaining with the next filter
            filterChain.doFilter(request, response);
            return;
        }
        // Extracting the token
        String jwt = authHeader.substring(7);
        // Checking if the token is valid and getting the necessary user details from it
        User user;
        try {
            user = jwtService.getUserFromToken(jwt);
            log.info("-------------- User: "+user);
        }catch (JwtException exception){
            throw new JwtException("invalid token");
//            handleJwtException(request, response, filterChain);
//            return;
        }
        // Setting the security context for the gotten user
        if (user.getUsername() != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Handling JwtException
     * Setting the apiException
     * and the required elements into the response
     *
     * ******* lors du test avec Postman on reçoit la reponse en tont que json
     *          mais la reponse reçu par angular est un string
     * */
    protected void handleJwtException(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException, ServletException {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                jsonMapper.objectToJson(
                        ResponseEntity.status(status).body("Invalid token!")
                )
        );
        filterChain.doFilter(request, response);
    }
}
