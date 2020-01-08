package com.example.accessingdatabasemysql.dao;

import com.example.accessingdatabasemysql.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface  UserRepository
        extends CrudRepository<User, Integer> {
    User findOneById(Integer id);
}
