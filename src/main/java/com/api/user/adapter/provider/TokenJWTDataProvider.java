package com.api.user.adapter.provider;

import com.api.user.adapter.provider.repository.UserEntity;
import com.api.user.usecase.gateway.TokenGateway;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenJWTDataProvider implements TokenGateway {

  private final String secret;

  public TokenJWTDataProvider(String secret) {
    this.secret = secret;
  }

  public String generate(UserEntity user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer("User API")
          .withSubject(user.getEmail())
          .withExpiresAt(expirationDate())
          .sign(algorithm);
    } catch (JWTCreationException exception){
      throw new RuntimeException(exception.getMessage());
    }
  }

  public String verify(String tokenJWT) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
          .withIssuer("User API")
          .build()
          .verify(tokenJWT)
          .getSubject();

    } catch (JWTVerificationException exception){
      throw new RuntimeException(exception.getMessage());
    }
  }

  private Instant expirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
