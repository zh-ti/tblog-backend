package com.tian.tblog;

import com.tian.tblog.bean.DocCategory;
import com.tian.tblog.service.DocCategoryService;
import com.tian.tblog.service.DocumentService;
import com.tian.tblog.service.impl.DocCategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.Doc;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class BackendSpringbootApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    DocCategoryServiceImpl docCategoryService;

    @Autowired
    DocumentService documentService;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getConnection());

//        System.out.println(docCategoryService.queryDocCategory("cfe5fe3d"));

//        System.out.println(docCategoryService.addDocCategory("test"));

//        List<DocCategory> list = docCategoryService.docCategoryList();
//
//        for (DocCategory docCategory : list) {
//            System.out.println(docCategory);
//        }

    }

    @Test
    public void test1(){
    }

    @Test
    public void test2(){
        List<DocCategory> list = docCategoryService.docCategoryList();

        for (DocCategory docCategory : list) {
            System.out.println(docCategory);
        }
    }

}
