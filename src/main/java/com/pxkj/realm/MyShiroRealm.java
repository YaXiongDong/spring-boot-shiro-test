package com.pxkj.realm;

import com.pxkj.entity.SysUser;
import com.pxkj.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();

        String username = sysUser.getUsername();
        List<String> roleList = userService.getRoles(username);
        List<String> permissionList = userService.getPermissions(username);

        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>(permissionList.size());

        roleSet.addAll(roleList);
        permissionSet.addAll(permissionList);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permissionSet);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        SysUser sysUser = userService.getUserByName(username);

        if (sysUser == null){
            throw  new UnknownAccountException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, sysUser.getPassword(), getName());

        return authenticationInfo;
    }
}
