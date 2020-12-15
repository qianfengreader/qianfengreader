package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.Cart;

import javax.servlet.http.HttpServletRequest;

public interface CartService {

    BaseResp findAll(HttpServletRequest request);

    BaseResp getPrice(Object ids);

    BaseResp insert(Cart cart);

    BaseResp delete(Integer id);

    BaseResp getUserInfo(HttpServletRequest request);
}
