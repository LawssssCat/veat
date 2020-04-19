
package com.vshop.veat.mapper;

import com.vshop.veat.entity.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/17 17:57
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    /**
     * 1. 根据id查询
     * 2. 更新数据，检查更新日期
     * 3. 根据type查找
     */
    @Test
    @Transactional
    public void selectByType_updateTime() {
        // 根据id查找
        ProductCategory productCategory = productCategoryMapper.selectById(1);
        log.info(productCategory.toString());
        // 更新
        productCategory.setProductCategoryType(10);
        log.info(String.valueOf(productCategoryMapper.updateById(productCategory)));
        // 根据type查找
        Collection<ProductCategory> productCategories = productCategoryMapper.selectByProductCategoryTypeList(Arrays.asList(new Integer[]{10})).values();
        log.info(productCategories.toString());
    }

    @Test
    public void selectListByType() {
        // 找到全部type
        List<ProductCategory> categories = productCategoryMapper.selectList(null);
        log.info("size:{}", categories.size());
        log.info(categories.toString());
        Set<Integer> types = new HashSet<>();
        for (ProductCategory category : categories) {
            types.add(category.getProductCategoryType());
        }
        // 根据type查找
        Collection<ProductCategory> cs = productCategoryMapper.selectByProductCategoryTypeList(types).values();
        log.info("size:{}", cs.size());
        log.info(cs.toString());
        // 空查找
        Collection<ProductCategory> cs2 = productCategoryMapper.selectByProductCategoryTypeList(null).values();
        log.info(cs2.toString());
    }

}

