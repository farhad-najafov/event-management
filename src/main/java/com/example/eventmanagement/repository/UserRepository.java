package com.example.eventmanagement.repository;

import com.example.eventmanagement.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String userName);

  boolean existsByUsername(String username);

}
