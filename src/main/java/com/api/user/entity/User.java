package com.api.user.entity;

public class User {
  private String name;
  private String email;
  private String password;
  private UserRole role;

  public User(String name, String email, String password, UserRole role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public boolean isValid() {
    if(name == null || name.isEmpty()) {
      return false;
    }

    if(email == null || email.isEmpty()) {
      return false;
    }

    if(password == null || password.isEmpty() || password.length() < 8) {
      return false;
    }

    if(role == null) {
      return false;

    }
    return true;
  }
}
