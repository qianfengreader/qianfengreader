package com.qf.dao;

import com.qf.common.BaseResp;
import com.qf.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> findLunBoTu();

    Book findByIdLunBoTu(@Param("bid")Integer bid);

    List<Book> findLimit6();

    List<Book> findAllLimit20();

    List<Book> findAllByTimeLimit20();

    List<Book> findRandom2(@Param("random1") Integer random1);

    List<Book> findAllBangDanFromTicket();

    List<Book> findAllBangDanFromMoods();

    List<Book> findAllBangDanFromTime();

    List<Book> findAllBangDanFromXuan(@Param("random1") Integer random1);

    List<Book> findAllBangDanFromWordsNum();

    List<Book> findByTypeId(@Param("tid") Integer id);

    List<Book> findBySearch(@Param("search") String search);

    Book findById(@Param("bid") Integer id);

}
