package com.api.user.usecase.gateway;

import com.api.user.usecase.model.UserModel;
import com.api.user.usecase.model.UserStoreRequestModel;

public interface UserGateway {
  UserModel store(UserStoreRequestModel requestModel);
}
