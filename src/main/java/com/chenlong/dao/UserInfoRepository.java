package com.chenlong.dao;


import com.chenlong.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
