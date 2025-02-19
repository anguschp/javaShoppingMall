package com.angus.springbootmall.service.Implement;

import com.angus.springbootmall.dao.UserDao;
import com.angus.springbootmall.dto.UserRegisterRequest;
import com.angus.springbootmall.model.User;
import com.angus.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
