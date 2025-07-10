package com.example.eventmanagement.model.response;

import com.example.eventmanagement.dto.EventDto;
import com.example.eventmanagement.dto.RoleDto;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

@Data
public class UserResponse {

  private Long id;
  private String username;
  private Set<RoleDto> roles;
  private Set<EventDto> events;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
