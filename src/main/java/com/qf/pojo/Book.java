package com.qf.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "book")
@ExcelTarget("book") /*标识  唯一区分  无实际意义*/
public class Book implements Serializable {/*序列化*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Excel(name = "图书编号")//
    private Integer bid;
    @Excel(name = "图书名字")//
    @Column(name = "bookname")
    private String bookname;
    @Excel(name = "作者")//
    @Column(name = "author")
    private String author;
    @Excel(name = "类型")//
    @Column(name = "type")
    private String type;
    @Excel(name = "价格")//
    @Column(name = "price")
    private String price;
    @Excel(name = "字数")//
    @Column(name = "wordsnum")
    private Double wordsnum; //字数
    //
    @Column(name = "img")
    private String img;
    @Excel(name = "简介")//
    @Column(name = "info")
    private String info;    //图书简介
    @Excel(name = "状态")//
    @Column(name = "state")
    private String state;   //状态
    @Excel(name = "阅读人数")//
    @Column(name = "moods")
    private Double moods;   //阅读人数
    @Excel(name = "是否vip")//
    @Column(name = "isvip")
    private String isvip;  //是否vip
    @Excel(name = "用户选票")//
    @Column(name = "ticket")
    private Integer ticket; //用户选票
    @Excel(name = "发布日期",format = "yyyy年MM月dd日")//
    @Column(name = "btime")
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date btime;     //发布日期
    @Column(name = "comment")
    private  String comment;//评论
    @Excel(name = "目录")
    @Column(name = "mulu")
    private String mulu;
    @Column(name = "body")
    private String body;//内容 链接
}
