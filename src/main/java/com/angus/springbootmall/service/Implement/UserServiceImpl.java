package com.angus.springbootmall.service.Implement;

import com.angus.springbootmall.dao.UserDao;
import com.angus.springbootmall.dto.UserRegisterRequest;
import com.angus.springbootmall.model.User;
import com.angus.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        String inputEmail = userRegisterRequest.getEmail();

        User user = userDao.getUserByEmail(inputEmail);


        //check email if existed, if yes: return 400, if no: create new user account
        if(user != null){
            log.warn("Email already in use :{}" , userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Email already in use");
        }

        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
