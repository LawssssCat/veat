package com.vshop.veat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vshop.veat.entity.Product;
import com.vshop.veat.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 18:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private IProductService iProductService;

    /**
     * 1. 查询上架的商品
     * 2. 分页查询全部商品
     */
    @Test
    public void findUpAll() {
        // 查询上架
        List<Product> upAll = iProductService.findUpAll();
        log.info(upAll.toString());

        // 查询分页
        IPage<Product> page = iProductService.page(new Page<>(1, 1));
        log.info("获得分页对象");
        List<Product> records = page.getRecords();
        log.info(records.toString());
    }
}
