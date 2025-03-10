package com.scb.interview.m1microservice;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    public Map<String, Object> extractUserInfo(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            throw new IllegalStateException("No JWT token found");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", jwt.getClaimAsString("preferred_username"));
        userInfo.put("email", jwt.getClaimAsString("email"));
        userInfo.put("roles", jwt.getClaimAsMap("realm_access").get("roles"));
        userInfo.put("collectionGrantCount", 0); // Ensure key exists
        userInfo.put("displayName", jwt.getClaimAsString("name"));

        return userInfo;
    }
}
