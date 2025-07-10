package com.example.eventmanagement.controller;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import com.example.eventmanagement.dto.EventDto;
import com.example.eventmanagement.entity.User;
import com.example.eventmanagement.model.request.EventRequest;
import com.example.eventmanagement.model.response.EventResponse;
import com.example.eventmanagement.service.EventService;
import com.example.eventmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Events", description = "Event Management APIs")
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;
  private final UserService userService;



  @SecurityRequirement(name = "bearerAuth")
  @Operation(
      summary = "Get all events",
      description = "Returns a list of all available events"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of events successfully retrieved"),
      @ApiResponse(responseCode = "404", description = "Resource not found"),
      @ApiResponse(responseCode = "500", description =  "Internal server error")
  })
  @GetMapping
  public ResponseEntity<List<EventResponse>> getAllEvents() {
    return ResponseEntity.ok(eventService.getAllEvents());
  }




  @SecurityRequirement(name = "bearerAuth")
  @Operation(
      summary = "Get event by ID",
      description = "Returns event by ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Event successfully retrieved"),
      @ApiResponse(responseCode = "404", description = "Resource not found"),
      @ApiResponse(responseCode = "500", description =  "Internal server error")
  })
  @GetMapping("/{id}")
  public ResponseEntity<EventResponse> getEventById(@PathVariable Long id) {
    return ResponseEntity.ok(eventService.getEventById(id));
  }



  @SecurityRequirement(name = "bearerAuth")
  @Operation(
      summary = "Create a new event",
      description = "Creates a new event with provided details"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Event successfully created"),
      @ApiResponse(responseCode = "400", description = "Validation error"),
      @ApiResponse(responseCode = "500", description =  "Internal server error")
  })
  @PostMapping
  public ResponseEntity<EventResponse> createEvent(@RequestBody @Valid EventRequest eventRequest) {
    return ResponseEntity.ok(eventService.createEvent(eventRequest));
  }


  @SecurityRequirement(name = "bearerAuth")
  @Operation(
      summary = "Update an existing event",
      description = "Updates an existing event by ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Event successfully updated"),
      @ApiResponse(responseCode = "400", description = "Validation error"),
      @ApiResponse(responseCode = "404", description = "Event not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")

  })
  @PutMapping("/{id}")
  public ResponseEntity<EventResponse> updateEvent(@PathVariable Long id ,@RequestBody @Valid
  EventRequest eventRequest) {
    return ResponseEntity.ok(eventService.updateEvent(id,eventRequest));
  }


  @SecurityRequirement(name = "bearerAuth")
  @Operation(
      summary = "Delete an event",
      description = "Delete an event by its ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Event successfully deleted"),
      @ApiResponse(responseCode = "404", description = "Event not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
    eventService.deleteEvent(id);
    return ResponseEntity.noContent().build();
  }


  @SecurityRequirement(name = "bearerAuth")
  @Operation(
      summary = "Register user to event",
      description = "Registers the currently authenticated user to the event with the specified ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User registered successfully"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "404", description = "User or event not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @PostMapping("/{eventId}/register")
  public ResponseEntity<String> registerToEvent(@PathVariable Long eventId, Principal principal) {
    String username = principal.getName();
    User user = userService.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username not found with username: " + username));

    userService.registerToEvent(user.getId(), eventId);
    return ResponseEntity.ok("User registered to event successfully");
  }
}
