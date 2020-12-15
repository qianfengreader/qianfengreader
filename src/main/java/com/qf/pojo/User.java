package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    @Column(name = "name")
    private String name;        //昵称
    @Column(name = "username")
    private String username;    //用户名
    @Column(name = "password")
    private String password;    //密码
    @Column(name = "email")
    private String email;         //邮箱
    @Column(name = "tel")
    private String tel;         //电话
    @Column(name = "dragonmoney")
    private Integer dragonmoney;    //子龙币
    @Column(name = "isvip")
    private String isvip;   //身份(普通用户   vip)
    @Column(name = "img")
    private String img;    //图片
    @Column(name = "fans")
    private Integer fans;    //粉丝数
    @Column(name = "focus")
    private Integer focus;  //关注数
    @Column(name = "ismanager")
    private Boolean ismanager;   //身份(1管理员 0不是管理员)

    //不入库
    private String code;

}
