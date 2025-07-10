package com.example.eventmanagement.service;

import com.example.eventmanagement.model.request.EventRequest;
import com.example.eventmanagement.model.response.EventResponse;
import java.util.List;

public interface EventService {
  EventResponse getEventById(Long id);
  List<EventResponse> getAllEvents();
  EventResponse createEvent(EventRequest eventRequest);
  EventResponse updateEvent(Long id, EventRequest eventRequest);
  void deleteEvent(Long id);
}
