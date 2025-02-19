package com.angus.springbootmall.dao.Implement;

import com.angus.springbootmall.dao.SqlFactory;
import com.angus.springbootmall.dao.UserDao;
import com.angus.springbootmall.dto.UserRegisterRequest;
import com.angus.springbootmall.model.User;
import com.angus.springbootmall.rowmapper.userRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate JdbcTemplate;

    @Autowired
    private SqlFactory sqlFactory;

    @Override
    public Integer createUser(UserRegisterRequest registerReq) {

        HashMap<String, Object> params = new HashMap<String, Object>();

        KeyHolder keyHolder = new GeneratedKeyHolder();


        JdbcTemplate.update(sqlFactory.sql_createUser(registerReq , params) , new MapSqlParameterSource(params) , keyHolder);

        int generatedId = keyHolder.getKey().intValue();

        return generatedId;
    }

    @Override
    public User getUserById(Integer userId) {

        HashMap<String, Object> params = new HashMap<>();

        List<User> result = JdbcTemplate.query(sqlFactory.sql_getUserById(userId, params) ,  params, new userRowMapper());

        if(result.size() > 0)
        {
            return result.get(0);
        }else {
            return null;
        }

    }
}
