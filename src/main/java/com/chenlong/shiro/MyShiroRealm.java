package com.chenlong.shiro;

import com.chenlong.domain.Permission;
import com.chenlong.domain.Role;
import com.chenlong.domain.User;
import com.chenlong.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo");
        //获取用户的输入的账号
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        User user = userInfoService.findByUserName(username);
        System.out.println("userInfo=" + user);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("MyShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        for (Role role : user.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getRole());
            for (Permission p : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 将权限对象中的权限code取出.
     *
     * @param permissions
     * @return
     */
//    public Set<String> getStringPermissions(Set<Permission> permissions) {
//        Set<String> stringPermissions = new HashSet<String>();
//        if (permissions != null) {
//            for (Permission p : permissions) {
//                stringPermissions.add(p.getPermission());
//            }
//        }
//        return stringPermissions;
//    }
}
