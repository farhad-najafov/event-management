package com.example.eventmanagement.mapper;

import com.example.eventmanagement.dto.RoleDto;
import com.example.eventmanagement.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

  RoleDto toDto(Role role);
  Role toEntity(RoleDto roleDto);

}
