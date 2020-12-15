package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.common.BaseResp;
import com.qf.dao.CartMapper;
import com.qf.dao.OrderMapper;
import com.qf.pojo.Cart;
import com.qf.pojo.User;
import com.qf.service.CartService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public BaseResp findAll(HttpServletRequest request) {

        BaseResp baseResp = new BaseResp();

        String token = getToken(request);
        Object o = redisUtils.get(token);
        Object o1 = JSONObject.toJSON(o);
        Cart cart = JSONObject.parseObject(o1.toString(), Cart.class);

        if (StringUtils.isEmpty(cart)) {
            baseResp.setCode(201);
            baseResp.setMessage("添加购物车失败");
            return baseResp;
        }

        List<Cart> cartList = cartMapper.findAllByUsername(cart.getUsername());

        if (cartList == null || cartList.size() == 0) {
            baseResp.setCode(202);
            baseResp.setMessage("您还没有添加购物车，请选择您喜欢的书！");
            return baseResp;
        }

        baseResp.setCode(200);
        baseResp.setMessage("获取购物车信息成功！");
        baseResp.setData(cartList);
        return baseResp;
    }

    @Override
    public BaseResp getPrice(Object ids) {

        BaseResp baseResp = new BaseResp();
        List<Integer> id = (List) ids;
        Double price = 0.00;

        for (int i =0; i < id.size(); i++) {

            Cart byId = cartMapper.findById(id.get(i));
            price += byId.getPrice();
        }
        baseResp.setData(price);
        baseResp.setCode(200);
        return baseResp;
    }

    @Override
    public BaseResp insert(Cart cart) {

        BaseResp baseResp = new BaseResp();
        if (cart != null) {
            cartMapper.insert(cart);
            baseResp.setCode(200);
            baseResp.setMessage("添加成功");
            return baseResp;
        }
        baseResp.setMessage("添加失败");
        baseResp.setCode(201);
        return baseResp;
    }

    @Override
    public BaseResp delete(Integer id) {

        BaseResp baseResp = new BaseResp();
        if (id != null) {
            //Integer cid = Integer.valueOf(id);
            cartMapper.delete(id);
            baseResp.setCode(200);
            baseResp.setMessage("删除成功！");
            return baseResp;
        }
        baseResp.setMessage("删除失败");
        baseResp.setCode(201);
        return baseResp;
    }

    @Override
    public BaseResp getUserInfo(HttpServletRequest request) {

        BaseResp baseResp = new BaseResp();
        String token = getToken(request);
        Object o = redisUtils.get(token);
        Object o1 = JSONObject.toJSON(o);
        User user = JSONObject.parseObject(o1.toString(), User.class);

        baseResp.setCode(200);
        baseResp.setData(user);
        return baseResp;
    }


    public String getToken(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie co : cookies) {
            if (co.getName().equals("token")){
                token=co.getValue();
            }
        }
        return token;
    }
}
