package com.example.eventmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(name = "created_at",  updatable = false)
  private LocalDateTime createAt;

  @Column(name = "updated_at")
  private LocalDateTime updateAt;

  @PrePersist
  protected void prePersist() {
    this.createAt = LocalDateTime.now();
    this.updateAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void preUpdate() {
    this.updateAt = LocalDateTime.now();
  }
}
