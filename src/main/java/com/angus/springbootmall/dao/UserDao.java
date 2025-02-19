package com.angus.springbootmall.dao;

import com.angus.springbootmall.dto.UserRegisterRequest;
import com.angus.springbootmall.model.User;

public interface UserDao {


    public Integer createUser(UserRegisterRequest registerReq);

    public User getUserById(Integer userId);

    public User getUserByEmail(String email);
}
