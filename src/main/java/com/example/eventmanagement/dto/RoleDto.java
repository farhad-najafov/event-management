package com.example.eventmanagement.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RoleDto {

  private Long id;
  private String name;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
