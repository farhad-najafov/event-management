package com.example.eventmanagement.service.impl;

import com.example.eventmanagement.dto.EventDto;
import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.exception.ResourceNotFoundException;
import com.example.eventmanagement.mapper.EventMapper;
import com.example.eventmanagement.model.request.EventRequest;
import com.example.eventmanagement.model.response.EventResponse;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.service.EventService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final EventMapper eventMapper;


  @Override
  public EventResponse getEventById(Long id) {

    Event event = eventRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Event not found with id: " + id));

    EventDto eventDto = eventMapper.toDto(event);
    return eventMapper.toResponse(eventDto);
  }

  @Override
  public List<EventResponse> getAllEvents() {
    List<Event> events = eventRepository.findAll();
    return events.stream()
        .map(eventMapper::toDto)
        .map(eventMapper::toResponse)
        .toList();
  }

  @Override
  public EventResponse createEvent(EventRequest eventRequest) {
    Event event = eventMapper.toEntity(eventRequest);
    Event savedEvent = eventRepository.save(event);
    EventDto eventDto = eventMapper.toDto(savedEvent);
    return eventMapper.toResponse(eventDto);
  }

  @Override
  public EventResponse updateEvent(Long id, EventRequest eventRequest) {
    Event existingEvent = eventRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Event not found with id: " + id));

    Event updatedEvent = eventMapper.toEntity(eventRequest);

    updatedEvent.setCreatedAt(existingEvent.getCreatedAt());

    Event  savedEvent = eventRepository.save(updatedEvent);
    EventDto eventDto = eventMapper.toDto(savedEvent);

    return eventMapper.toResponse(eventDto);
  }

  @Override
  public void deleteEvent(Long id) {
    Event event = eventRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Event not found with id: " + id));
    eventRepository.delete(event);

  }
}
