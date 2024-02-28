package com.mdina.location.security;

import com.mdina.location.config.ApiConfig;
import com.mdina.location.service.mappers.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * changing the default spring security configurations
 * and customize them for a stateless authentication
 */
@Configuration
@EnableWebSecurity
// Support modeling at the method level.
// Allow @PreAuthorize, @PostAuthorize, @PreFilter, and @PostFilter annotations
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JsonMapper jsonMapper;
    private final ApiConfig apiConfig;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)

                // Specifying the permitted paths
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(
                                        apiConfig.getVersion()+"/test/**").permitAll()
                                .anyRequest().authenticated()
                )

                // Setting the session management type to stateless
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Setting ore JwtAuthenticationFilter
                // before the UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                // Setting the HTTPS for all the endpoints
//                .requiresChannel(
//                        channel -> channel.requestMatchers("/**").requiresSecure()
//                )

                // Handling http security exception (delegate it to the exception handler level)
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(
                                        (request, response, exception) -> {
                                            HttpStatus status = HttpStatus.UNAUTHORIZED;
                                            response.setStatus(status.value());
                                            response.setContentType("application/json");
                                            response.setCharacterEncoding("UTF-8");
                                            response.getWriter().write(jsonMapper.objectToJson(ResponseEntity.status(status).body("Invalid token!")));
                                        }
                                )
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * Configuration du CrossOrigin
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
