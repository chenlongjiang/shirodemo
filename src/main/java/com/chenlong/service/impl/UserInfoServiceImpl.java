package com.chenlong.service.impl;

import com.chenlong.dao.UserInfoRepository;
import com.chenlong.domain.User;
import com.chenlong.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public User findByUserName(String username) {
        System.out.println("UserInfoServiceImpl.findByUserName");
        return userInfoRepository.findByUsername(username);
    }
}
