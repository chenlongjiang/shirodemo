package com.chenlong.service;


import com.chenlong.domain.User;

public interface UserInfoService {
    User findByUserName(String username);
}
