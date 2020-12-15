package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.service.BookService;
import com.qf.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    //查询全部  后台
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public BaseResp findAll(){
        return bookService.findAll();
    }
    //轮播图查询6张
    @RequestMapping(value = "/findLunBoTu",method = RequestMethod.GET)
    public BaseResp findLunBoTu(){
        return bookService.findLunBoTu();
    }
    //轮播图变化后查询图书信息
    @RequestMapping(value = "/findByIdLunBoTu",method = RequestMethod.POST)
    public BaseResp findByIdLunBoTu(@RequestBody Map map){
        return bookService.findByIdLunBoTu(Integer.valueOf(map.get("id").toString()));
    }
    //轮播图下 查询6个图书信息
    @RequestMapping(value = "/findLimit6",method = RequestMethod.GET)
    public BaseResp findLimit6(){
        return bookService.findLimit6();
    }
    //查询20个
    @RequestMapping(value = "/findAllLimit20",method = RequestMethod.GET)
    public BaseResp findAllLimit20(){
        return bookService.findAllLimit20();
    }
    //根据日期查询最前面的20个
    @RequestMapping(value = "/findAllByTimeLimit20",method = RequestMethod.GET)
    public BaseResp findAllByTimeLimit20(){
        return bookService.findAllByTimeLimit20();
    }
    //随机查询2个
    @RequestMapping(value = "/findRandom2",method = RequestMethod.GET)
    public BaseResp findRandom2(){
        return bookService.findRandom2();
    }
    //查询所有分类
    @Autowired
    BookTypeService bookTypeService;
    @RequestMapping(value = "/findAllBookType",method = RequestMethod.GET)
    public BaseResp findAllBookType(){
        return bookTypeService.findAll();
    }
    //查询id对应book
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public BaseResp findById(@RequestBody Map map){
        return bookService.findById(Integer.valueOf(map.get("bid").toString()));
    }
    /*---------------榜单---------------*/
    @RequestMapping(value = "/findAllBangDanFromTicket",method = RequestMethod.GET)
    public BaseResp findAllBangDanFromTicket(){
        return bookService.findAllBangDanFromTicket();
    }

    @RequestMapping(value = "/findAllBangDanFromMoods",method = RequestMethod.GET)
    public BaseResp findAllBangDanFromMoods(){
        return bookService.findAllBangDanFromMoods();
    }

    @RequestMapping(value = "/findAllBangDanFromTime",method = RequestMethod.GET)
    public BaseResp findAllBangDanFromTime(){
        return bookService.findAllBangDanFromTime();
    }

    @RequestMapping(value = "/findAllBangDanFromXuan",method = RequestMethod.GET)
    public BaseResp findAllBangDanFromXuan(){   //suiji
        return bookService.findAllBangDanFromXuan();
    }

    @RequestMapping(value = "/findAllBangDanFromWordsNum",method = RequestMethod.GET)
    public BaseResp findAllBangDanFromWordsNum(){   //字数
        return bookService.findAllBangDanFromWordsNum();
    }
    /*----------------/---------------------*/
    /*---------------查找 类型---------------*/
    @RequestMapping(value = "/findByTypeId/{tid}/{page}/{size}",method = RequestMethod.GET)
    public BaseResp findByTypeId(@PathVariable("tid")Integer tid, @PathVariable("page")Integer page, @PathVariable("size")Integer size){
        return bookService.findByTypeId(Integer.valueOf(tid),page,size);
    }





}
