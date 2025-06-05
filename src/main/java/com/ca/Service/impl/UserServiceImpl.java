package com.ca.Service.impl;

import com.ca.Respository.UserRepository;
import com.ca.Service.UserService;
import com.ca.model.User;
import com.ca.model.dto.LoginResponseDTO;
import com.ca.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRespository;

    public UserServiceImpl(UserRepository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmailFld());
        user.setName(userDTO.getFirstNameFld() + " " + userDTO.getLastNameFld());
        return userRespository.save(user);


    }

    @Override
    public void fcmToken(String fcmToken, LoginResponseDTO loginResponseDTO) {
    }
}
