package com.ca.model.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private Long userId;
    private String userName;
}
