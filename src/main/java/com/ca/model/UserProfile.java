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

package com.ca.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(name = "userprofile_tbl")
public class UserProfile {
  @Id
  @Column(name = "OID_PKFld")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long oidPkFld;

  @Column(name = "UserID")
  private Long userId;

  @Column(name = "Password_Fld")
  private String passwordFld;

  @Column(name = "FcmToken")
  private String fcmToken;
}
