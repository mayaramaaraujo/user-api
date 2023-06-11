package com.api.user.usecase.gateway;

import com.api.user.adapter.provider.repository.UserEntity;

public interface TokenGateway {
  String generate(UserEntity user);
  String verify(String token);
}
