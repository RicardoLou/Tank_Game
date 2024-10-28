package com.Ricardo.DAO;

import com.Ricardo.bean.user;

import java.util.ArrayList;

public interface IUserDAO {
    //查询单个用户
    public user selectUser();
    //注册 添加一条用户信息
    public boolean insertUser();
}
