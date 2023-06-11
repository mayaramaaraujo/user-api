package com.api.user.usecase;

import com.api.user.entity.User;
import com.api.user.entity.UserRole;
import com.api.user.usecase.gateway.UserGateway;
import com.api.user.usecase.model.NewUserResponseModel;
import com.api.user.usecase.model.UserModel;
import com.api.user.usecase.model.UserStoreRequestModel;

public class NewUserUseCase {

  private final UserGateway userGateway;

  public NewUserUseCase(UserGateway userGateway) {
    this.userGateway = userGateway;
  }

  public NewUserResponseModel execute(UserStoreRequestModel requestModel) {
    try {
      User user = new User(
          requestModel.name(),
          requestModel.email(),
          requestModel.password(),
          UserRole.fromString(requestModel.role())
      );

      if(!user.isValid()) {
        throw new RuntimeException("Invalid user");
      }

      UserModel userModel = userGateway.store(requestModel);

      return new NewUserResponseModel(userModel, true, null);
    } catch (Exception e) {
      return new NewUserResponseModel(null, false, e.getMessage());
    }
  }
}
