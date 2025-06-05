package com.ca.model.dto;

import lombok.Data;

public @Data class LoginDTO {

  private String email;

  private String password;
  private String fcmToken;
}
