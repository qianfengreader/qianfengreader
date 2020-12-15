package com.qf.service.impl;

import com.qf.common.AliPay;
import com.qf.common.BaseResp;
import com.qf.dao.CartMapper;
import com.qf.dao.OrderMapper;
import com.qf.pojo.Cart;
import com.qf.pojo.Order;
import com.qf.service.OrderService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    RedisUtils redisUtils;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CartMapper cartMapper;

    @Override
    public BaseResp pay(HttpServletRequest request, HttpServletResponse response, Object ids)throws ServletException, IOException {

       /* BaseResp baseResp = new BaseResp();
        String token = getToken(request);
        Object o = redisUtils.get(token);
        //将object转为TBUSER对象
        Object o1 = JSONObject.toJSON(o);
        //再用JSONObject.parseObject
        User user = JSONObject.parseObject(o1.toString(), User.class);
        List id  = (List)ids;*/


        BaseResp baseResp = new BaseResp();
        List<Integer> id = (List) ids;
        Double totalMonunt = 0.00;

        for (int i =0; i < id.size(); i++) {

            Cart byId = cartMapper.findById(id.get(i));
            totalMonunt += byId.getPrice();

            //在生成订单的同时,删除购物车信息
            cartMapper.delete(id.get(i));
        }


        /*//声明总价格
        Double totalMonunt = 0.00;
        for (Object i:id
        ) {
            Object hget = redisUtils.hget("shop_" + user.getUid(), i.toString());
            Object o2 = JSONObject.toJSON(hget);
            Cart cart = JSONObject.parseObject(o2.toString(), Cart.class);
            totalMonunt=cart.getPrice()+totalMonunt;
        }*/


        //生成订单号
        UUID uuid = UUID.randomUUID();
        String string = uuid.toString();
        String tradno = string.replace("-", "");
        //将uuid和总价，还有信息，存储到数据库，生成一条未支付的订单信息
        Order order = new Order();
        order.setTotalmount(totalMonunt);
        order.setStatus("WAIT_PAY");
        order.setTransferid(tradno);
        orderMapper.insertOrder(order);

        AliPay aliPay = new AliPay();
        String pay = aliPay.pay(request, response, order);
        baseResp.setData(pay);
        return baseResp;
    }

    @Override
    public BaseResp findByStatus() {
        BaseResp baseResp = new BaseResp();
        List<Order> byStatus = orderMapper.findByStatus();
        for (Order oo:byStatus
             ) {
            System.out.println(oo);
        }
        baseResp.setData(byStatus);
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
