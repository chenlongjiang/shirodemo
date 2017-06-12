package com.chenlong.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @RequestMapping("userList")
    public String userInfo() {
        return "userInfo";
    }

    @RequestMapping("userAdd")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd() {
        return "userInfoAdd";
    }

    @RequestMapping("userDel")
    @RequiresPermissions("userInfo:del")
    public String userInfoDel() {
        return "userInfoDel";
    }
}
