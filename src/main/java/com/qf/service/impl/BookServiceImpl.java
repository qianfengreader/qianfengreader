package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.BookRepository;
import com.qf.pojo.Book;
import com.qf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;


    @Override
    public BaseResp findAll() {
        BaseResp baseResp = new BaseResp();
        List<Book> all = bookRepository.findAll();
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        baseResp.setData(all);
        return baseResp;
    }
}
