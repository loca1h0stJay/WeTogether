package com.we.dao;

import com.we.entity.user.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserDAO extends MongoRepository<UserEntity,String> {
}
