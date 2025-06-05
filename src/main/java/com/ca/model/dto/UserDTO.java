/**
 * 
 ***********************************************************************************
 * Copyright (c) 2024-2025 NexSigma Technologies. All Rights Reserved.
 *
 * This is proprietary information of NexSigma Technologies.
 ************************************************************************************
 * 
*/

package com.ca.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {






    private String firstNameFld;

    private String middleNameFld;

    private String lastNameFld;

    private String emailFld;

    private String passwordFld;



}