package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qf.dao.OrderMapper;
import com.qf.pojo.Order;
import com.qf.service.CartService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController

public class AliPayController {
    /*@Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/pay")
    public void pay (HttpServletRequest request, HttpServletResponse response,@RequestBody Map map) throws IOException, ServletException {
        Optional<Book> id = bookRepository.findById((Integer) map.get("id"));
       *//* if (id==null){

        }*//*
        Book book = id.get();
        AliPay aliPay = new AliPay();
        String form = aliPay.pay(request,response,book);
        response.getWriter().write(form); //直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

    }*/

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CartService cartService;

    @RequestMapping("/returnUrl")
    public void returnUrl(HttpServletRequest request , HttpResponse httpResp) throws AlipayApiException {
        Map<String, String> stringStringMap = convertRequestParamsToMap(request);

        boolean signVerified = AlipaySignature.rsaCheckV1(stringStringMap, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtRoAUwPe9qYIlNBEQZtAQ6QDgFjSO/ajXNkeAvbX0YdoqhXIXv9K8fitfY8JBeLEWo4OA3clkl4V64ISyCSGdNJI2iUkeC/0tNL6VvhnV7WOxKyfLGqEXZLd1gQJPu2hQ7s0fbDkvzoJPSgAcBxu6/JpTqsxw7J7jl+uGBvAHxtce4cmXBhfwC7vSM7e9xrF8eNuySz/yKM3/308XhdKWqmd7VFifDj4Jb38a5/RM1yDpGY4zB/pyXHbKdLO2o0JZHYRFM0r/Tg6YVaYoeuQwwwdi6V31nHLDEr1YueUBky0SFIEP+JICeplhQ8HpuiWIq17yRXD2glvLyWN6HIxqwIDAQAB", "utf-8", "RSA2"); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            System.out.println(stringStringMap);

            if (stringStringMap.get("trade_status").equals("TRADE_SUCCESS")){
                //根据out_trade_no 修改 订单状态为已支付
                String out_trade_no = stringStringMap.get("out_trade_no");
                //通过订单号去数据库查询出该笔订单
                Order bytransferId = orderMapper.findBytransferid(out_trade_no);
                //比对金额
                Double total_amount =Double.valueOf(stringStringMap.get("total_amount")) ;

                if (total_amount.toString().equals(bytransferId.getTotalmount().toString())){
                    bytransferId.setStatus("PAY_SUCCESS");
                    bytransferId.setPayid(stringStringMap.get("trade_no"));
                    orderMapper.updateById(bytransferId);

                }
            }
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }
    }

    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }
}
