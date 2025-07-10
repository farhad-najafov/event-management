package com.example.eventmanagement.mapper;

import com.example.eventmanagement.dto.UserDto;
import com.example.eventmanagement.entity.User;
import com.example.eventmanagement.model.request.UserRequest;
import com.example.eventmanagement.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = (RoleMapper.class))
public interface UserMapper {

  UserDto toDto(User user);
  User toEntity(UserDto userDto);
  UserResponse toResponse(UserDto userDto);
  User requestToEntity(UserRequest userRequest);
  UserDto requestToDto(UserRequest userRequest);
}
