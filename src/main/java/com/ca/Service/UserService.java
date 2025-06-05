package com.ca.Service;

import com.ca.model.User;
import com.ca.model.dto.LoginResponseDTO;
import com.ca.model.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface UserService {
    User createUser(UserDTO userDTO);
    void fcmToken(String fcmToken, LoginResponseDTO loginResponseDTO);

}
