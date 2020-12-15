package com.qf.myrealm;

import com.qf.dao.UserRepository;
import com.qf.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {


    @Autowired
    UserRepository userRepository;


    //鉴权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取前端页面用户输入的账号
        String username =(String) principalCollection.getPrimaryPrincipal();
        //通过账号查出该用户的权限




        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取前端输入的用户名
        String username =(String) authenticationToken.getPrincipal();
        //通过用户名查出密码,再和用户输入的密码做比对
        User byUsername = userRepository.findByUsername(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo =null;
        if (byUsername !=null){
            String password = byUsername.getPassword();
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,getName());
        }
        return simpleAuthenticationInfo;
    }

}
