package com.qf.pojo;

import lombok.Data;

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
    @Column(name = "price")
    private Integer price;
    @Column(name = "wordnum")
    private Double wordnum; //字数
    @Column(name = "img")
    private String img;
    @Column(name = "info")
    private String info;    //图书简介
    @Column(name = "state")
    private String state;   //状态
    @Column(name = "moods")
    private Double moods;   //阅读人数
    @Column(name = "isvip")
    private Boolean isvip;  //是否vip
    @Column(name = "ticket")
    private Integer ticket; //用户选票
    @Column(name = "btime")
    private Date btime;     //发布日期
    @Column(name = "comment")
    private  String comment;//评论
}
