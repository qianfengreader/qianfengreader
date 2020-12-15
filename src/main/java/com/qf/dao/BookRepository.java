package com.qf.dao;

import com.qf.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface BookRepository extends JpaRepository<Book,Integer> {
}
