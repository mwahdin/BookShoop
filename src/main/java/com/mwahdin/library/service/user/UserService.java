package com.mwahdin.library.service.user;

import com.mwahdin.library.dto.request.UserRequest;
import com.mwahdin.library.dto.response.UserResponse;

public interface UserService {
    UserResponse save(UserRequest userRequest);

}
