package com.qf.dao;

import com.qf.common.BaseResp;
import com.qf.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper
public interface BookRepository extends JpaRepository<Book,Integer> {

}
