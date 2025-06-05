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

package com.ca.Service.impl;

import com.ca.Respository.UserProfileRepository;
import com.ca.Respository.UserRepository;
import com.ca.Service.AuthService;
import com.ca.Service.UserService;
import com.ca.model.User;

import com.ca.model.UserProfile;
import com.ca.model.dto.LoginDTO;
import com.ca.model.dto.LoginResponseDTO;
import com.ca.model.dto.UserDTO;
import com.ca.provider.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AuthServiceImpl implements AuthService {
	
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);


    private final UserService userService;
    private final UserRepository userRespository;
    private final UserProfileRepository userProfileRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
                           UserService userService, UserRepository userRespository,
                           UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider)
    {
        this.userService = userService;
        this.userRespository = userRespository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User createUser(UserDTO userDTO) {
    	logger.info("-->createUser()");
    	
        User user = this.userService.createUser(userDTO);
        String encodedSalt = encodePassword(userDTO.getPasswordFld());
        UserProfile userProfile = new UserProfile();
        userProfile.setPasswordFld(encodedSalt.split("###")[1]);
        userProfileRepository.save(userProfile);
        return user;
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        User user = userRespository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Invalid Login"));

        UserProfile userProfile = userProfileRepository.findByUserId(user.getOidPkFld()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String providedPassword = loginDTO.getPassword();
        if (!passwordEncoder.matches(providedPassword, userProfile.getPasswordFld())) {
            logger.error("Invalid password for this Email: {}", loginDTO.getEmail());
            throw new BadCredentialsException("Invalid password");
        }
        return createLoginResponse(user);
    }

    private LoginResponseDTO createLoginResponse(User user) {
        String token = jwtTokenProvider.createToken(user.getOidPkFld());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(token);
        loginResponseDTO.setUserId(user.getOidPkFld());
        loginResponseDTO.setUserName(user.getName());
        return loginResponseDTO;
    }

    private String encodePassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = passwordEncoder.encode(encodedSalt + password);
        return encodedSalt + "###" + hashedPassword;
    }
}