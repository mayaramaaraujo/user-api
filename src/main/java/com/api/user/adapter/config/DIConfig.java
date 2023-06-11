package com.api.user.adapter.config;

import com.api.user.adapter.provider.AuthorizationDataProvider;
import com.api.user.adapter.config.security.SecurityFilter;
import com.api.user.adapter.provider.TokenJWTDataProvider;
import com.api.user.adapter.provider.UserDataProvider;
import com.api.user.adapter.provider.repository.UserRepository;
import com.api.user.usecase.AuthenticationUseCase;
import com.api.user.usecase.NewUserUseCase;
import com.api.user.usecase.gateway.SecurityFilterGateway;
import com.api.user.usecase.gateway.TokenGateway;
import com.api.user.usecase.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DIConfig {

  @Value("${api.security.token.secret}")
  private String secret;

  UserRepository userRepository;

  public DIConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserGateway userGateway() {
    return new UserDataProvider(userRepository, passwordEncoder());
  }

  @Bean
  public NewUserUseCase newUserUseCase() {
    return new NewUserUseCase(userGateway());
  }

  @Bean
  TokenGateway tokenGateway() {
    return new TokenJWTDataProvider(secret);
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new AuthorizationDataProvider(userRepository);
  }

  @Bean
  SecurityFilterGateway securityFilterGateway() {
    return new SecurityFilter(tokenGateway(), userRepository);
  }

  @Bean
  public AuthenticationUseCase authenticationUseCase() {
    return new AuthenticationUseCase(tokenGateway());
  }
}
