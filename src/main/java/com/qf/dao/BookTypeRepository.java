package com.qf.dao;

import com.qf.pojo.BookType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface BookTypeRepository extends JpaRepository<BookType,Integer> {
}
