/**
 * 
 ***********************************************************************************
 * Copyright (c) 2024-2025 NexSigma Technologies. All Rights Reserved.
 *
 * This is proprietary information of NexSigma Technologies.
 ************************************************************************************
 * 
*/

/**
 * 
 */

package com.ca.Service;

import com.ca.model.User;
import com.ca.model.dto.LoginDTO;
import com.ca.model.dto.LoginResponseDTO;
import com.ca.model.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface AuthService {
    User createUser(UserDTO userDTO);
    LoginResponseDTO login(LoginDTO loginDTO);
}
