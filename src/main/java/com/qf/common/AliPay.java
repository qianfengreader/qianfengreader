package com.qf.common;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.qf.pojo.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AliPay {

    public String pay(HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse, Order order) throws ServletException, IOException {
        AlipayClient alipayClient =  new DefaultAlipayClient( "https://openapi.alipaydev.com/gateway.do" , "2021000116661245", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCItZM/jVqCxGYKUCuTSsasIdboxcPBRs6zJ/MGbuzxF4YPEoRFvYSB9L5yJfyg3IP0/K9nW0zLO0Mm0FCNAx/RvaXbfJBnKAk+KDTdYGdiIStE9VJzhG2wL4QAzyEaicDWZEmyZUHVhqa3d9fUQo1BttYeQpO6Ahp8Og4kruI6JAnsJ7QBvbVQXUSqUhLnGEDZAAKVLgD1eVH++nWETIWF2GtmLGszKpF2ev43+ZfiSlfWg1hSb7k1AHcwPDqZyqSEa/q4yPs4Wg7N8qvqPigCl+poOuUulieEmgD2s+3tjSEj+JBuE3U6O9AG/+WHg/XuTP41ktpuAbmlePgQPWMtAgMBAAECggEAWF7nfGFnbLiALZJ9WI0xt/t37KhKThnza6QbWMFDsOU/52ZU0rAbg9YtdaRQlbt2IVtmFy3Ril4YnnJyX3TxkKdDG4p93fDnaKKc6a1zpt9CFSVv0vR74zQT5GIASCNb4Dqb2RHTaK/N/TMe9TrADQafipgG3mqVrE/q7JOP8Xx4RDWv2uRJQeJqJdm4Sd0/CodUL3jnT9Y161coemVqpjy1yNhb0MlWTnQZZA+zYT6Pta+UJQL65oF8DNkMTUofj76VLwwKmrdxl4F7noS4qw6Hp6xDS/PbPXLWfdsVxAd780MPFuNRqm3B+gueAhE8epLAemQ9DnDDLwlxZ9TWgQKBgQDysGvHCVEXzWm/mRHTmwKfwpgJ8GzTSUwk4sPtNs+Rpqb+R79yGB2PEvhtRCCjxNUq3IJ5q2ul/p0835Jma+1/orfWmRYFZaspiBzgRq5uwwvSjv1dWDYyMNNBTQ11MHSgXM0yAxdpIzDT0zUxnLDteDEzzU0XOIY6+y3bicUIuQKBgQCQNRlrpkntpqeMdlY6+Rg1yq/RDtYqpYtWbx1Yy96GycHBAjRiOG+0+AFGJBAdwb1MLjGi/mB7rCw/FkIstIIoU67wSlSDKTqUpKQnDi4A2G/FoMzsBt4vxBavIA6INrgPG2/npte9A7u/p2sg5XBNKGblqnMcjfzBBGBtkIMMFQKBgQDmx69l0U1FFjf2U+go0mIXbCPKZ9AEx9HZza5E+HdAaTqp2a6LH7WUJZUJytxC/9056gFFFCbE7i+2AGCmdwGc+y5YqrDPJxQx5VaYS356o42th/4VctnutpFdqpkelJqDGFykr13mw+kdtd4CtJ9m4oHfYd5qM30pv0unVgaBOQKBgBFtWVUZE3YU3cqrwl/+Gl7lz7mEAX3F48DeTL2eZZYEa9XXXl1AVL+Z+Q1w7kGqWCYMrxLzitVnSTGWjwi4B8K32jxPv0bahKnR+8R8gjK39Un81MbxTYiYOeWQCjGL61JoDm4AS7Ib8WSH5vpiv9+TMlwu7RpWTmVFEiIrhKrBAoGAWFac5qXDwJ+lihoCbFGpoT82hOGSKZIrlVzRQNt7cjCg9FOkULLJGc2DI9xe36rkjJNXRscJqlqS8Yk9FKUu+a0X6ZbfT93ts7VX7DuPRHccXUsd+8a3v2FLHHgwEtF+SPpxuKdhAxsG+relMdqBGm4q2FP5dyQ2Gy2Z6s5WGvc=", "json", "utf-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiLWTP41agsRmClArk0rGrCHW6MXDwUbOsyfzBm7s8ReGDxKERb2EgfS+ciX8oNyD9PyvZ1tMyztDJtBQjQMf0b2l23yQZygJPig03WBnYiErRPVSc4RtsC+EAM8hGonA1mRJsmVB1Yamt3fX1EKNQbbWHkKTugIafDoOJK7iOiQJ7Ce0Ab21UF1EqlIS5xhA2QAClS4A9XlR/vp1hEyFhdhrZixrMyqRdnr+N/mX4kpX1oNYUm+5NQB3MDw6mcqkhGv6uMj7OFoOzfKr6j4oApfqaDrlLpYnhJoA9rPt7Y0hI/iQbhN1OjvQBv/lh4P17kz+NZLabgG5pXj4ED1jLQIDAQAB", "RSA2");  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:8080/#/cartlist");
        alipayRequest.setNotifyUrl("http://3r6077149n.eicp.vip/returnUrl"); //在公共参数中设置回跳和通知地址

        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+order.getTransferid()+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":\""+order.getTotalmount()+"\","  +
                "    \"subject\":\"欢迎购买\","  +
                "    \"body\":\"欢迎购买\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + "utf-8");
        /*httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();*/
        return form;
    }

}