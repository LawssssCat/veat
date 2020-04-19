package com.vshop.veat.mapper;

import com.vshop.veat.entity.Product;
import com.vshop.veat.enums.ProductStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 16:57
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    /**
     * 1. 存储 一个状态0
     * 2. 查询 状态0
     */
    @Test
    @Transactional
    public void saveTest() {
        // 插入
        Product product = new Product()
                .setProductId(UUID.randomUUID().toString().substring(0, 31))
                .setProductName("廋肉粥")
                .setProductPrice(new BigDecimal(3.2))
                .setProductStock(100)
                .setProductCategoryType(5)
                .setProductDescription("好吃")
                .setProductIcon("https://raw.githubusercontent.com/LawssssCat/piggo-vscode/master/images/timg%20(20).jpg")
                .setProductStatus(ProductStatusEnum.DOWN);
        int row = productMapper.insert(product);
        log.info("row={}", row);
        // 查询
        List<Product> products = productMapper.selectListByProductStatus(ProductStatusEnum.UP);
        log.info("size={}", products.size());
        log.info(products.toString());
    }

}
