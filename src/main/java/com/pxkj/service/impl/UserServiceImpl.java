package com.pxkj.service.impl;

import com.pxkj.dao.UserDao;
import com.pxkj.entity.SysUser;
import com.pxkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public SysUser getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public List<String> getRoles(String username) {
        return userDao.getRoles(username);
    }

    @Override
    public List<String> getPermissions(String username) {
        return userDao.getPermissions(username);
    }
}
