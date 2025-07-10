package com.example.eventmanagement.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

@Data
public class UserDto {
  private Long id;
  private String username;
  private String password;
  private Set<RoleDto> roles;
  private Set<EventDto> events;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
