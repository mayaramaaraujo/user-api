package com.api.user.usecase.gateway;

import jakarta.servlet.http.HttpServletRequest;

public interface SecurityFilterGateway {
  String retrieveToken(HttpServletRequest request);
}
