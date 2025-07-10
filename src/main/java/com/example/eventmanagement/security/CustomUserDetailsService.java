package com.example.eventmanagement.security;

import com.example.eventmanagement.entity.User;
import com.example.eventmanagement.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user =userRepository.findByUsername(username);

    return user.map(CustomUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
