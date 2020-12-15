package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.Book;

import java.util.List;

public interface BookService {
    //查询全部
    BaseResp findAll();
    //轮播图查询6张
    BaseResp findLunBoTu();
    //轮播图变化后查询图书信息
    BaseResp findByIdLunBoTu(Integer bid);
    //轮播图下 查询6个图书信息
    BaseResp findLimit6();
    //查询20个
    BaseResp findAllLimit20();
    //根据日期查询最前面的20个
    BaseResp findAllByTimeLimit20();
    //随机查找2个book
    BaseResp findRandom2();
    /*------------------榜单----------------------*/
    BaseResp findAllBangDanFromTicket();

    BaseResp findAllBangDanFromMoods();

    BaseResp findAllBangDanFromTime();

    BaseResp findAllBangDanFromXuan();

    BaseResp findAllBangDanFromWordsNum();
    /*---------------------------book.vue----------------------*/
    BaseResp findById(Integer bid);

    //查找 对应类型所有的书
    BaseResp findByTypeId(Integer tid, Integer page, Integer size);


}
