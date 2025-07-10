package com.example.eventmanagement.model.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EventRequest {

  @NotBlank(message = "Event name must not be blank")
  private String name;

  @NotBlank(message = "Location must not be blank")
  private String location;

  @NotNull(message = "Event date is required")
  private LocalDate eventDate;

  @NotNull(message = "Start time is required")
  @Future(message = "Start time must be in the future")
  private LocalDateTime startTime;

  @NotNull(message = "End time is required")
  @Future(message = "End time must be in the future")
  private LocalDateTime endTime;
}
