package com.qf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bid;
    @Column(name = "bookname")
    private String bookname;
    @Column(name = "author")
    private String author;
    @Column(name = "type")
    private String type;
    /*撒丹化科技暗杀数据库挂伤筋动骨阿*/
    @Column(name = "price")
    private String price;
    @Column(name = "wordsnum")
    private Double wordsnum; //字数
    @Column(name = "img")
    private String img;
    @Column(name = "info")
    private String info;    //图书简介
    @Column(name = "state")
    private String state;   //状态
    @Column(name = "moods")
    private Double moods;   //阅读人数
    @Column(name = "isvip")
    private String isvip;  //是否vip
    @Column(name = "ticket")
    private Integer ticket; //用户选票
    @Column(name = "btime")
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date btime;     //发布日期
    @Column(name = "comment")
    private  String comment;//评论
    @Column(name = "body")
    private String body;//内容 链接
}
