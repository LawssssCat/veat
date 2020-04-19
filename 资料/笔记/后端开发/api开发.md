
更前端协商的api文档如下：


```yml
server:
  servlet:
    context-path: /sell
```

# 工具类


**JsonResult**
```java

```



# API

###商品列表

```
GET /sell/buyer/product/list
```

参数

```
无
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "name": "热榜",
            "type": 1,
            "foods": [
                {
                    "id": "123456",
                    "name": "皮蛋粥",
                    "price": 1.2,
                    "description": "好吃的皮蛋粥",
                    "icon": "http://xxx.com",
                }
            ]
        },
        {
            "name": "好吃的",
            "type": 2,
            "foods": [
                {
                    "id": "123457",
                    "name": "慕斯蛋糕",
                    "price": 10.9,
                    "description": "美味爽口",
                    "icon": "http://xxx.com",
                }
            ]
        }
    ]
}
```

```java
package com.vshop.veat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vshop.veat.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 同一个类型的产品集合
 *
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 20:18
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class ProductsWithCategoryVo {
    /**
     * 产品分类名
     */
    @JsonProperty("name")
    private String categoryName;
    /**
     * 产品分类编号
     */
    @JsonProperty("type")
    private Integer categoryType;
    /**
     * 查询出来的产品详情
     */
    @JsonProperty("foods")
    private List<ProductVo> products;

    /**
     * @param products     产品集合的数据源
     * @param categoryName 产品类型名
     * @param categoryType 产品类型标识
     * @return 一个类型的产品集合vo
     */
    public ProductsWithCategoryVo(Collection<? extends ProductVo> products, String categoryName, Integer categoryType) {
        this.setCategoryName(categoryName)
                .setCategoryType(categoryType)
                .setProducts(products instanceof List ? (List) products : new ArrayList(products));
    }

    public ProductsWithCategoryVo(String categoryName, Integer categoryType, Collection<? extends Product> products) {
        this(ProductVo.buildList(products), categoryName, categoryType);
    }
}

```
```java
package com.vshop.veat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vshop.veat.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 商品详情
 *
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 20:24
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class ProductVo {
    @JsonProperty("id")
    private String productId;
    /**
     * 产品名称
     */
    @JsonProperty("name")
    private String productName;
    /**
     * 产品价格
     */
    @JsonProperty("price")
    private BigDecimal productPrice;
    /**
     * 产品介绍
     */
    @JsonProperty("description")
    private String productDescription;
    /**
     * 产品图标
     */
    @JsonProperty("icon")
    private String productIcon;

    /**
     * po集合封装成vo集合
     */
    public static List<ProductVo> buildList(Collection<? extends Product> products) {
        List<ProductVo> list = new ArrayList<>();
        for (Product product : products) {
            list.add(new ProductVo(product));
        }
        return list;
    }

    /**
     * po对象封装成vo
     */
    public ProductVo(Product product) {
        this.setProductId(product.getProductId())
                .setProductDescription(product.getProductDescription())
                .setProductIcon(product.getProductIcon())
                .setProductName(product.getProductName())
                .setProductPrice(product.getProductPrice());
    }

}

```

这里的重点是不能循环查询数据库。

```java
package com.vshop.veat.controller;


import com.vshop.veat.entity.Product;
import com.vshop.veat.service.IProductCategoryService;
import com.vshop.veat.service.IProductService;
import com.vshop.veat.vo.JsonResult;
import com.vshop.veat.vo.ProductsWithCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 买家商品
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IProductCategoryService iProductCategoryService;

    @GetMapping("/list")
    public JsonResult list() {
        // 1. 查询所有的上架商品
        List<Product> allProducts = iProductService.findUpAll();

        // 2. 对传入的分类，并封装返回
        List<ProductsWithCategoryVo> productsWithCategoryVoList = iProductCategoryService.sortWithCategory(allProducts);

        return JsonResult.ok().data(productsWithCategoryVoList);
    }

}

```
```java
package com.vshop.veat.service.impl;

import com.vshop.veat.entity.Product;
import com.vshop.veat.entity.ProductCategory;
import com.vshop.veat.mapper.ProductCategoryMapper;
import com.vshop.veat.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vshop.veat.vo.ProductVo;
import com.vshop.veat.vo.ProductsWithCategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
@Slf4j
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductsWithCategoryVo> sortWithCategory(List<Product> products) {
        Map<Integer, List<Product>> productMap = products.stream()
                // 按类型分类
                .collect(Collectors.groupingBy(Product::getProductCategoryType));

        // 数据库查找相关的类型
        Map<Integer, ProductCategory> categoryMap = productCategoryMapper.selectByProductCategoryTypeList(productMap.keySet());

        //  把全部信息按类型封装返回

        List<ProductsWithCategoryVo> result =
                // 映射成封装类型
                productMap.keySet().stream()
                        .map((categoryKey) -> {
                            ProductCategory category = categoryMap.get(categoryKey);
                            // TODO 如果没有，就弄个空的先
                            if (null == category) {
                                category = new ProductCategory();
                            }
                            // 封装
                            return new ProductsWithCategoryVo(
                                    ProductVo.buildList(productMap.get(categoryKey)),
                                    category.getProductCategoryName(),
                                    categoryKey
                            );
                        })
                        // 返回数组类型
                        .collect(Collectors.toList());
        return result;
    }
}

```

###创建订单

```
POST /sell/buyer/order/create
```

参数

```
name: "张三"
phone: "18868822111"
address: "慕课网总部"
openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
items: [{
    productId: "1423113435324",
    productQuantity: 2 //购买数量
}]

```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "orderId": "147283992738221" 
  }
}
```

###订单列表

```
GET /sell/buyer/order/list
```

参数

```
openid: 18eu2jwk2kse3r42e2e
page: 0 //从第0页开始
size: 10
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": [
    {
      "orderId": "161873371171128075",
      "buyerName": "张三",
      "buyerPhone": "18868877111",
      "buyerAddress": "慕课网总部",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    },
    {
      "orderId": "161873371171128076",
      "buyerName": "张三",
      "buyerPhone": "18868877111",
      "buyerAddress": "慕课网总部",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    }]
}
```

###查询订单详情

```
GET /sell/buyer/order/detail
```

参数

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": {
          "orderId": "161899085773669363",
          "buyerName": "李四",
          "buyerPhone": "18868877111",
          "buyerAddress": "慕课网总部",
          "buyerOpenid": "18eu2jwk2kse3r42e2e",
          "orderAmount": 18,
          "orderStatus": 0,
          "payStatus": 0,
          "createTime": 1490177352,
          "updateTime": 1490177352,
          "orderDetailList": [
            {
                "detailId": "161899085974995851",
                "orderId": "161899085773669363",
                "productId": "157875196362360019",
                "productName": "招牌奶茶",
                "productPrice": 9,
                "productQuantity": 2,
                "productIcon": "http://xxx.com",
                "productImage": "http://xxx.com"
            }
        ]
    }
}
```

###取消订单

```
POST /sell/buyer/order/cancel
```

参数

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

###获取openid

```
重定向到 /sell/wechat/authorize
```

参数

```
returnUrl: http://xxx.com/abc  //【必填】
```

返回

```
http://xxx.com/abc?openid=oZxSYw5ldcxv6H0EU67GgSXOUrVg
```


