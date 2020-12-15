package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.User;

public interface UserService {
    //查询email 是否被使用
    BaseResp sendMail(String email);

    BaseResp registy(User user);

    BaseResp login(User user);

    BaseResp findByUsername(String username);
}
