package com.ca.api.controller;

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

import java.io.IOException;

@Controller
@RequestMapping("/api/auth/oauth")
public class OAuthController {


    private final DefaultOAuth2AuthorizationRequestResolver resolver;

    public OAuthController(ClientRegistrationRepository clientRegistrationRepository,
                           @Value("${spring.security.oauth2.client.registration.google.redirect-uri}") String redirectUri) {
        this.resolver = new DefaultOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository, "/api/auth/oauth");
        this.resolver.setAuthorizationRequestCustomizer(customizer -> customizer.redirectUri(redirectUri));
    }


    @Operation(summary = "Trigger Google OAuth Login")
    @GetMapping("/google")
    public void googleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OAuth2AuthorizationRequest authRequest = resolver.resolve(request, "google");
        if (authRequest != null) {
            response.sendRedirect(authRequest.getAuthorizationRequestUri());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid OAuth2 Request");
        }
    }


}
