package com.andy.shiro.chapter2;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

    public String getName() {
        return "myrealm1";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        /**
         * 仅支持UsernamePasswordToken类型Token
         */
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         * 得到用户名
         */
        String username = (String)token.getPrincipal();
        /**
         * 得到密码
         */
        String password = new String((char[])token.getCredentials());
        if(!"zhang".equals(username)) {
            /**
             * 如果用户名错误
             */
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)) {
            /**
             * 如果密码错误
             */
            throw new IncorrectCredentialsException();
        }
        /**
         * 如果身份认证成功，返回一个AuthenticationInfo实现
         */
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
