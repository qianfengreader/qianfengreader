package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.common.BaseResp;
import com.qf.dao.BookMapper;
import com.qf.dao.BookRepository;
import com.qf.pojo.Book;
import com.qf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper bookMapper;

    @Override
    public BaseResp findAll() {
        BaseResp baseResp = new BaseResp();
        List<Book> all = bookRepository.findAll();
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        baseResp.setData(all);
        return baseResp;
    }
    //轮播图6张图片展示
    @Override
    public BaseResp findLunBoTu() {
        BaseResp baseResp = new BaseResp();
        List<Book> lunBoTu = bookMapper.findLunBoTu();
        baseResp.setData(lunBoTu);
        baseResp.setMessage("查询成功");
        baseResp.setCode(200);
        return baseResp;
    }
    //轮播图变化后查询图书信息
    @Override
    public BaseResp findByIdLunBoTu(Integer bid) {
        BaseResp baseResp = new BaseResp();
        Book byIdLunBoTu = bookMapper.findByIdLunBoTu(bid);
        baseResp.setData(byIdLunBoTu);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        return baseResp;
    }
    //轮播图下 查询6个图书信息
    @Override
    public BaseResp findLimit6() {
        BaseResp baseResp = new BaseResp();
        List<Book> limit6 = bookMapper.findLimit6();
        baseResp.setData(limit6);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        return baseResp;
    }

    @Override
    public BaseResp findAllLimit20() {
        BaseResp baseResp = new BaseResp();
        List<Book> allLimit20 = bookMapper.findAllLimit20();
        baseResp.setCode(200);
        baseResp.setData(allLimit20);
        baseResp.setMessage("查询成功");
        return baseResp;
    }

    @Override
    public BaseResp findAllByTimeLimit20() {
        BaseResp baseResp = new BaseResp();
        List<Book> allByTimeLimit20 = bookMapper.findAllByTimeLimit20();
        baseResp.setMessage("查询成功");
        baseResp.setCode(200);
        baseResp.setData(allByTimeLimit20);
        return baseResp;
    }

    @Override
    public BaseResp findRandom2() {
        Random random = new Random();
        int i = random.nextInt(18)+1;
        List<Book> all = bookMapper.findRandom2(i);
        BaseResp baseResp = new BaseResp();
        baseResp.setData(all);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        return baseResp;
    }
    /*-------------------榜单---------------------------*/
    @Override
    public BaseResp findAllBangDanFromTicket() {
        BaseResp baseResp = new BaseResp();
        List<Book> allBangDanFromTicket = bookMapper.findAllBangDanFromTicket();
        baseResp.setData(allBangDanFromTicket);
        baseResp.setMessage("查询成功");
        baseResp.setCode(200);
        return baseResp;
    }

    @Override
    public BaseResp findAllBangDanFromMoods() {
        BaseResp baseResp = new BaseResp();
        List<Book> allBangDanFromMoods = bookMapper.findAllBangDanFromMoods();
        baseResp.setData(allBangDanFromMoods);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        return baseResp;
    }

    @Override
    public BaseResp findAllBangDanFromTime() {
        BaseResp baseResp = new BaseResp();
        List<Book> allBangDanFromTime = bookMapper.findAllBangDanFromTime();
        baseResp.setData(allBangDanFromTime);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        return baseResp;
    }

    @Override
    public BaseResp findAllBangDanFromXuan() {
        BaseResp baseResp = new BaseResp();
        Random random = new Random();
        int i = random.nextInt(15);
        List<Book> allBangDanFromXuan = bookMapper.findAllBangDanFromXuan(i);
        baseResp.setData(allBangDanFromXuan);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        return baseResp;
    }

    @Override
    public BaseResp findAllBangDanFromWordsNum() {
        BaseResp baseResp = new BaseResp();
        List<Book> allBangDanFromWordsNum = bookMapper.findAllBangDanFromWordsNum();
        baseResp.setData(allBangDanFromWordsNum);
        baseResp.setCode(200);
        baseResp.setMessage("查询成功");
        return baseResp;
    }

    /*-------------------------------------------*/

    @Override
    public BaseResp findById(Integer bid) {
        BaseResp baseResp = new BaseResp();
        Optional<Book> byId = bookRepository.findById(bid);
        if (byId.isPresent()){
            baseResp.setData(byId);
            baseResp.setCode(200);
            baseResp.setMessage("查询成功");
            return baseResp;
        }
        baseResp.setCode(201);
        baseResp.setMessage("查询失败");
        return baseResp;
    }

    @Override
    public BaseResp findByTypeId(Integer tid, Integer page, Integer size) {
        /*PageRequest pageRequest = new PageRequest(page - 1, size);*/
        List<Book> all = bookMapper.findByTypeId(tid);
        PageHelper.startPage(page,size);
        PageInfo<Book> bookPageInfo = new PageInfo<>(all);
        BaseResp baseResp = new BaseResp();
        baseResp.setData(all);//分页失败
        System.out.println(bookPageInfo);
        baseResp.setMessage("查询成功");
        baseResp.setCode(200);
        baseResp.setTotal(bookPageInfo.getTotal());
        return baseResp;
    }


}
