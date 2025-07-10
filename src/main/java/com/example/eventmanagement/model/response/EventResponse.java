package com.example.eventmanagement.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EventResponse {
  private Long id;
  private String name;
  private String location;
  private LocalDate eventDate;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
