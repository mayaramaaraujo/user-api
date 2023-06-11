package com.api.user.usecase.model;

import com.api.user.adapter.provider.repository.UserEntity;
import com.api.user.entity.UserRole;
import jakarta.validation.constraints.Email;

public record UserStoreRequestModel(String name, @Email String email, String password, String role) {
  public UserEntity toEntity(String encodePassword) {
    return new UserEntity(name, email, encodePassword, UserRole.fromString(role));
  }
}
