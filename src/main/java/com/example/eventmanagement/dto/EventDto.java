package com.example.eventmanagement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EventDto {
  private Long id;
  private String name;
  private String location;
  private LocalDate eventDate;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
