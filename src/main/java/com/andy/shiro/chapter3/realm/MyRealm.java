package com.andy.shiro.chapter3.realm;

import com.andy.shiro.chapter3.permission.BitPermission;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("role2");
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("+user2+10");
        authorizationInfo.addStringPermission("user2:*");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
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