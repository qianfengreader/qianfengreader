package com.qf;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.qf.pojo.Book;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestEasyPoi {

    public List<Book> getBook(){
        ArrayList<Book> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setBid(i);
            book.setBookname("haha");
            list.add(book);
        }
        return list;
    }

    @Test
    public void testExcel() throws IOException {

        List<Book> books = getBook();
        /*导出excel*/
        //参数1  导出配置对象   参数2  导出类型   参数3 导出 数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("book全部信息", "book信息"), Book.class, books);
        //将excel写入指定位置  name 中填入 导出路径  .xml文件
        workbook.write(new FileOutputStream("C:/Users/大帅罗罗罗/Desktop/mywork/poi"));
        workbook.close(); //关流
    }

    @Test
    public void testImportExcel(){

    }
}
