package com.api.user.entity;

import java.util.Objects;

public enum UserRole {
  USER,
  ADMIN;

  public static UserRole fromString(String role) {
    if(Objects.equals(role.toUpperCase(), "ADMIN")) {
      return UserRole.ADMIN;
    } else {
      return UserRole.USER;
    }
  }
}
