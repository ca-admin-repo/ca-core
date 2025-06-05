package com.ca.api.controller;

import com.ca.Service.AuthService;
import com.ca.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/oauth", produces = MediaType.APPLICATION_JSON_VALUE)
public class OAuthController {


    private final AuthService authService;

    public OAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/verify-token")
    public ResponseEntity<?> verifyGoogleToken(@RequestBody Map<String, String> request) {
        String idToken = request.get("id_token");
        if (idToken == null) {
            return ResponseEntity.badRequest().body("Missing id_token");
        }

        try {
            User user = authService.verifyGoogleTokenAndLogin(idToken);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }


}
