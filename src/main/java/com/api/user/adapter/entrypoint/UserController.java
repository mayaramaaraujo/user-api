package com.api.user.adapter.entrypoint;

import com.api.user.adapter.provider.repository.UserEntity;
import com.api.user.usecase.AuthenticationUseCase;
import com.api.user.usecase.NewUserUseCase;
import com.api.user.usecase.model.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

  private final AuthenticationManager authenticationManager;
  private final NewUserUseCase newUserUseCase;
  private final AuthenticationUseCase authenticationUseCase;

  public UserController(AuthenticationManager authenticationManager, NewUserUseCase newUserUseCase, AuthenticationUseCase authenticationUseCase) {
    this.authenticationManager = authenticationManager;
    this.newUserUseCase = newUserUseCase;
    this.authenticationUseCase = authenticationUseCase;
  }

  @PostMapping
  public ResponseEntity<UserModel> store(@RequestBody @Valid UserStoreRequestModel request) {
    NewUserResponseModel response = newUserUseCase.execute(request);

    if(!response.success()) {
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(response.user());
  }

  @PostMapping("/login")
  public ResponseEntity<Object> auth(@RequestBody @Valid UserAuthRequestModel requestModel) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestModel.email(), requestModel.password());
    Authentication authentication = authenticationManager.authenticate(token);

    TokenModel tokenJWT = authenticationUseCase.execute((UserEntity) authentication.getPrincipal());

    if(tokenJWT.token().isEmpty()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    return ResponseEntity.ok(tokenJWT);
  }

}
