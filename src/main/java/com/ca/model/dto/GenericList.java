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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericList<T> {

  private String id;
  private String object = "list";
  private Long count;
  private List<T> data;

  public GenericList(List<T> data, Long count) {
    this.id = UUID.randomUUID().toString();
    this.data = data;
    this.count = count;
  }
}
