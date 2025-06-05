package com.ca.api.controller;

import com.ca.Service.AuthService;
import com.ca.model.User;
import com.ca.model.dto.GenericResponse;
import com.ca.model.dto.UserDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = "api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse<String>> createUser(@Valid @RequestBody UserDTO userDTO) {
        this.authService.createUser(userDTO);
        GenericResponse genericResponse = new GenericResponse<>();
        genericResponse.setMessage("User registered successfully!");
        return ResponseEntity.ok(genericResponse);


    }
}
