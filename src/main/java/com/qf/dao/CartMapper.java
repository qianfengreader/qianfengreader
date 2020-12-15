package com.qf.dao;

import com.qf.pojo.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("select * from cart where username = #{username}")
    List<Cart> findAllByUsername(String username);

    @Select("select * from cart where cartid = #{id}")
    Cart findById(Integer id);

    @Insert("insert into cart values (null,#{bookname},#{username},#{price},'未支付')")
    Integer insert(Cart cart);

    @Delete("delete from cart where cartid = #{cid}")
    Integer delete(Integer cid);
}
