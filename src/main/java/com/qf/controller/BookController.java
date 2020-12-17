package com.qf.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.qf.common.BaseResp;
import com.qf.common.UploadUtils;
import com.qf.pojo.Book;
import com.qf.pojo.BookMuLu;
import com.qf.service.BookService;
import com.qf.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UploadUtils uploadUtils;//图片上传
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
    /*-----head----*/
    @RequestMapping(value = "/findBySearch",method = RequestMethod.POST)
    public BaseResp findBySearch(@RequestBody Map map){
        return bookService.findBySearch(map.get("search").toString());
    }
    /*完本*/
    @RequestMapping(value = "/findAllByState",method = RequestMethod.GET)
    public BaseResp findAllByState(){
        return bookService.findAllByState();
    }
    //isvip  免费
    @RequestMapping(value = "/findByIsvip",method = RequestMethod.GET)
    public BaseResp findByIsvip(){
        return bookService.findByIsvip();
    }

    //导入excel
    @RequestMapping(value = "/importBookExcel",method = RequestMethod.POST)
    public BaseResp importBookExcel(MultipartFile excelFile) throws Exception {
        System.out.println("导入excel"+excelFile.getOriginalFilename());//输出文件名

        BaseResp baseResp = new BaseResp();
        //excel导入--------------------后台book
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);//标题占几行
        importParams.setHeadRows(1);//列占几行
        List<Book> book = ExcelImportUtil.importExcel(excelFile.getInputStream(), Book.class, importParams);
        for (Book o :
                book) {
            System.out.println(o);
        }
        //------------------------------

        return baseResp;
    }

    //导入excel-----目录
    @RequestMapping(value = "/importBookMuLu",method = RequestMethod.POST)
    public BaseResp importBookMuLu(@RequestBody Map map)  {
        try {
            return bookService.findByIdReturnBook(Integer.valueOf(map.get("bid").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //导入excel-----主体
    @RequestMapping(value = "/importBookNeiRong",method = RequestMethod.POST)
    public BaseResp importBookNeiRong(@RequestBody Map map)  {
        try {
            return bookService.findByIdReturnBody(Integer.valueOf(map.get("bid").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/saveAndFlush",method = RequestMethod.POST)
    public BaseResp saveAndFlush(@RequestBody Book book){
        System.out.println(book);
        return bookService.saveAndFlush(book);
    }
    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    public BaseResp deleteById(@RequestBody Map map){
        return bookService.deleteById(Integer.valueOf(map.get("bid").toString()));
    }

    /*图片  上传  */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public BaseResp upload(@RequestParam("file")MultipartFile multipartFile){
        return uploadUtils.upload(multipartFile);
    }






}
