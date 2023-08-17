package com.matheusdev.springbootrestapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matheusdev.springbootrestapi.entitties.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
