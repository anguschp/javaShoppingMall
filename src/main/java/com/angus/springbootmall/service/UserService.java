package com.angus.springbootmall.service;

import com.angus.springbootmall.dto.UserRegisterRequest;
import com.angus.springbootmall.model.User;

public interface UserService {

    public Integer register(UserRegisterRequest userRegisterRequest);

    public User getUserById(Integer userId);

}
