package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.pojo.Cart;
import com.qf.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public BaseResp findAll(HttpServletRequest request) {

        return cartService.findAll(request);
    }

    @RequestMapping(value = "/getPrice",method = RequestMethod.POST)
    public BaseResp getPrice(@RequestBody Map map) {

        return cartService.getPrice(map.get("ids"));
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public BaseResp insert(@RequestBody Cart cart) {

        return cartService.insert(cart);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public BaseResp delete(@PathVariable("id") Integer cartid) {

        return cartService.delete(cartid);
    }

    // 获取用户信息
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public BaseResp getUserInfo(HttpServletRequest request) {

        return cartService.getUserInfo(request);
    }
}
