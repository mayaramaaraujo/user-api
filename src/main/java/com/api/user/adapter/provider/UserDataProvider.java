package com.api.user.adapter.provider;

import com.api.user.adapter.provider.repository.UserEntity;
import com.api.user.adapter.provider.repository.UserRepository;
import com.api.user.usecase.gateway.UserGateway;
import com.api.user.usecase.model.UserModel;
import com.api.user.usecase.model.UserStoreRequestModel;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDataProvider implements UserGateway {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserDataProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserModel store(UserStoreRequestModel requestModel) {
    try {
      UserEntity user = requestModel.toEntity(passwordEncoder.encode(requestModel.password()));
      userRepository.save(user);
      return user.toModel();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

  }
}
