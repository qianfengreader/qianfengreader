package com.qf.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@ExcelTarget("bookmulu")
public class BookMuLu implements Serializable {
    @Excel(name = "目录")//
    private String muLu;
}
