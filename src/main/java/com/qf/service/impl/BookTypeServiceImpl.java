package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.BookTypeRepository;
import com.qf.pojo.BookType;
import com.qf.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    BookTypeRepository bookTypeRepository;
    @Override
    public BaseResp findAll() {
        BaseResp baseResp = new BaseResp();
        List<BookType> all = bookTypeRepository.findAll();
        baseResp.setMessage("增加成功");
        baseResp.setCode(200);
        baseResp.setData(all);
        return baseResp;
    }
}
