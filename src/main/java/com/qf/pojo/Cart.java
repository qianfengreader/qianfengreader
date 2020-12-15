package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartid;

    @Column(name = "bookname")
    private String bookname;

    @Column(name = "username")
    private String username;

    @Column(name = "price")
    private Double price;

    @Column(name = "state")
    private String state;
}
