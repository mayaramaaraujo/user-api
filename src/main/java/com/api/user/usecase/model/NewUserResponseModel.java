package com.api.user.usecase.model;

public record NewUserResponseModel(UserModel user, boolean success, String message) {
}
