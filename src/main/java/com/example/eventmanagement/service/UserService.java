package com.example.eventmanagement.service;

import com.example.eventmanagement.dto.UserDto;
import com.example.eventmanagement.entity.User;
import com.example.eventmanagement.model.request.UserRequest;
import com.example.eventmanagement.model.response.UserResponse;
import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<User> findByUsername(String userName);
  User save(User user);
  void registerToEvent(Long userId, Long eventId);

  UserResponse getById(Long userId);
  List<UserResponse> getAllUsers();
  UserResponse createUser(UserRequest userRequest);
  UserResponse updateUser(Long userId, UserRequest userRequest);
  void deleteUser(Long userId);
}
