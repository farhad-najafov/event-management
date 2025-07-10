package com.example.eventmanagement.config;

import com.example.eventmanagement.entity.Role;
import com.example.eventmanagement.entity.User;
import com.example.eventmanagement.repository.RoleRepository;
import com.example.eventmanagement.repository.UserRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserInitializer {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Bean
  CommandLineRunner initAdminUser() {
    return args -> {
      Role adminRole = roleRepository.findByName("ROLE_ADMIN")
          .orElseGet(()-> roleRepository.save(Role.builder()
              .name("ROLE_ADMIN")
              .build()));

      if (userRepository.findByUsername("admin").isEmpty()) {
        User user = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin123"))
            .roles(Collections.singleton(adminRole))
            .build();
        userRepository.save(user);
        System.out.println("Admin user created: admin / admin123");
      }
    };
  }

}
