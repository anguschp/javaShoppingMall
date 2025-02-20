package com.angus.springbootmall.service.Implement;

import com.angus.springbootmall.dao.UserDao;
import com.angus.springbootmall.dto.UserLoginRequest;
import com.angus.springbootmall.dto.UserRegisterRequest;
import com.angus.springbootmall.model.User;
import com.angus.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
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

        //generate password hash value
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }


    @Override
    public User userLogin(UserLoginRequest loginReq) {

        User user = userDao.getUserByEmail(loginReq.getEmail());

        if(user == null){
            log.warn("Email is not yet registered :{}" , loginReq.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Email is not yet registered");
        }

        String hashedInputPassword = DigestUtils.md5DigestAsHex(loginReq.getPassword().getBytes());


        if(user.getPassword().equals(hashedInputPassword))
        {
            log.info("Login success :{}" , loginReq.getEmail());
            return user;
        }
        else
        {
            log.warn("Password is incorrect :{}" , loginReq.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Password is incorrect");
        }

    }
}
