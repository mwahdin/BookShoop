package com.mwahdin.library.service;

import com.mwahdin.library.dto.request.UserRequest;
import com.mwahdin.library.dto.response.UserResponse;
import com.mwahdin.library.exception.RuleException;
import com.mwahdin.library.model.User;
import com.mwahdin.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        Optional<User> existResult = userRepository.findByUserName(userRequest.getUserName());
        if (existResult.isPresent()){
            throw new RuleException("USERNAME.IS.EXIST");
        }
        return createUserResponse(userRepository.save(createdUserRequest(userRequest)));
    }


    private UserResponse createUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .build();
    }

    private User createdUserRequest(UserRequest  userRequest){
        return User.builder()
                .passWord(userRequest.getPassWord())
                .userName(userRequest.getUserName())
                .build();
    }






























}