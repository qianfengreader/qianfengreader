package com.qf.dao;

import com.qf.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 54110 on 2020/12/11.
 */
@Mapper
public interface OrderMapper {

    Integer insertOrder(Order order);

    Order findBytransferid(@Param("transferid") String transferId);

    void updateById(Order bytransferid);

    //查询为支付的订单
    List<Order> findByStatus();
}
