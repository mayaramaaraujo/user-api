package com.api.user.adapter.provider;

import com.api.user.adapter.provider.repository.UserEntity;
import com.api.user.adapter.provider.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthorizationDataProvider implements UserDetailsService {

  private final UserRepository userRepository;

  public AuthorizationDataProvider(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email);
  }
}
