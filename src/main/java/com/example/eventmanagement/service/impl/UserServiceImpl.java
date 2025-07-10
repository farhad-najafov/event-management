package com.example.eventmanagement.service.impl;

import com.example.eventmanagement.dto.UserDto;
import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.entity.User;
import com.example.eventmanagement.mapper.UserMapper;
import com.example.eventmanagement.model.request.UserRequest;
import com.example.eventmanagement.model.response.UserResponse;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.repository.UserRepository;
import com.example.eventmanagement.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final EventRepository eventRepository;
  private final UserMapper userMapper;


  @Override
  public Optional<User> findByUsername(String userName) {
    return userRepository.findByUsername(userName);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void registerToEvent(Long userId, Long eventId) {
    User user = userRepository.findById(userId)
        .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId));

    Event event = eventRepository.findById(eventId)
        .orElseThrow(()-> new EntityNotFoundException("Event not found with id: " + eventId));

    user.getRegisteredEvents().add(event);
    event.getParticipants().add(user);
    userRepository.save(user);

  }

  @Override
  public UserResponse getById(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId));
    UserDto   userDto = userMapper.toDto(user);
    return userMapper.toResponse(userDto);

  }

  @Override
  public List<UserResponse> getAllUsers() {
    return userRepository.findAll().stream()
        .map(userMapper::toDto)
        .map(userDto -> userMapper.toResponse(userDto))
        .collect(Collectors.toList());
  }

  @Override
  public UserResponse createUser(UserRequest userRequest) {
    User user = userMapper.requestToEntity(userRequest);
    User savedUser = userRepository.save(user);
    UserDto userDto = userMapper.toDto(savedUser);
    return userMapper.toResponse(userDto);
  }

  @Override
  public UserResponse updateUser(Long userId, UserRequest userRequest) {
    User existingUser = userRepository.findById(userId)
        .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId));

  existingUser.setUsername(userRequest.getUsername());
  existingUser.setPassword(userRequest.getPassword());

  User updatedUser = userRepository.save(existingUser);
  UserDto userDto = userMapper.toDto(updatedUser);

    return userMapper.toResponse(userDto);
  }

  @Override
  public void deleteUser(Long userId) {
    User existingUser = userRepository.findById(userId)
        .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + userId));
    userRepository.delete(existingUser);

  }
}
