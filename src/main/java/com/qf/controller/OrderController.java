package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/pay")
    public BaseResp pay(HttpServletResponse response, HttpServletRequest request, @RequestBody Map map) throws ServletException, IOException {

        return orderService.pay(request, response, map.get("ids"));
    }

    @RequestMapping("/findByStatus")
    public BaseResp findByStatus (){
        return orderService.findByStatus();
    }
}
