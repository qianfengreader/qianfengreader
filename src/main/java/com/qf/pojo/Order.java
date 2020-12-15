package com.qf.pojo;

import lombok.Data;

/**
 * Created by 54110 on 2020/12/11.
 */
@Data
public class Order {

    private Integer id;

    private String transferid;

    private String payid;

    private Double  totalmount;

    private String status;
}
