package com.ca.api.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Central apps , admin@centralapps.in";
    }

    @GetMapping("/api/user")
    public Map<String, Object> user(Principal principal) {
        OAuth2User oAuth2User = (OAuth2User) principal;
        return oAuth2User.getAttributes();
    }
}

