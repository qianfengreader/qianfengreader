package com.qf.dao;

import com.qf.common.BaseResp;
import com.qf.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper
public interface BookRepository extends JpaRepository<Book,Integer> {
    /*自定义*/
    //查询 state 状态(完结)
    List<Book> findByState(String state);
    //查询 isvip 免费
    List<Book> findByIsvip(String vip);
    /*//删除
    void deleteByBid(Integer bid);*/

}
