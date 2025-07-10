package com.example.eventmanagement.mapper;

import com.example.eventmanagement.dto.EventDto;
import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.model.request.EventRequest;
import com.example.eventmanagement.model.response.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {

  EventDto toDto(Event event);
  Event toEntity(EventDto eventDto);

  EventResponse toResponse(EventDto eventDto);


  Event toEntity(EventRequest eventRequest);

  void updateEntityFromDto(EventDto eventDto, @MappingTarget Event event);
}
