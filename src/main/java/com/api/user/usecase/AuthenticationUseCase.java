package com.api.user.usecase;

import com.api.user.adapter.provider.repository.UserEntity;
import com.api.user.usecase.gateway.TokenGateway;
import com.api.user.usecase.model.TokenModel;

public class AuthenticationUseCase {
  private final TokenGateway tokenGateway;

  public AuthenticationUseCase(TokenGateway tokenGateway) {
    this.tokenGateway = tokenGateway;
  }

  public TokenModel execute(UserEntity userEntity) {
    try {
      String token = tokenGateway.generate(userEntity);
      return new TokenModel(token);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
