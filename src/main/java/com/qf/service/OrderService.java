package com.qf.service;

import com.qf.common.BaseResp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface OrderService {

    BaseResp pay(HttpServletRequest request, HttpServletResponse response, Object ids) throws ServletException, IOException;

    BaseResp findByStatus();
}
