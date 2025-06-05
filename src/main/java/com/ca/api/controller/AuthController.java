package com.ca.api.controller;


import com.ca.Service.AuthService;
import com.ca.Service.UserService;
import com.ca.model.dto.GenericResponse;
import com.ca.model.dto.LoginDTO;
import com.ca.model.dto.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
@CrossOrigin
@RestController
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);
        userService.fcmToken(loginDTO.getFcmToken(), loginResponseDTO);
        GenericResponse<LoginResponseDTO> genericResponse = new GenericResponse<>();
        genericResponse.setData(loginResponseDTO);
        return ResponseEntity.ok(genericResponse);
    }
}

