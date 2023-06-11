package com.api.user.usecase.model;

import com.api.user.entity.UserRole;

public record UserModel(String name, String email, UserRole role) {
}
