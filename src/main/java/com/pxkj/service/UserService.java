package com.pxkj.service;

import com.pxkj.entity.SysUser;

import java.util.List;

public interface UserService {

    SysUser getUserByName(String username);

    /**
     * 查询用户角色
     * @param username
     * @return
     */
    List<String> getRoles(String username);

    List<String> getPermissions(String username);

}
