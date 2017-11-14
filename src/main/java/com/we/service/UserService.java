package com.we.service;

import com.we.dao.UserDAO;
import com.we.entity.user.UserEntity;
import com.we.entity.user.param.UserCreateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public String createUser(UserCreateParam param){
        UserEntity userEntity= new UserEntity();
        userEntity.setDisplayName(param.displayName);
        userEntity.setPassword(param.password);
        userEntity.setUsername(param.username);
        userEntity.setUserType(param.type);
        try{
            userDAO.save(userEntity);
            return "0";
        }catch (Exception e){
            return "1";
        }
    }
}
