package com.mdina.location.service.services.interfaces;

import com.mdina.location.enumerations.Role;
import com.mdina.location.service.dto.User;

import java.security.Key;
import java.util.Map;

public interface IJwtService {
    String getUsername(String token);

    Long getUserId(String token);

    Role getUserRole(String token);

    User getUserFromToken(String jwt);

    User getAuthenticatedUser();
}
