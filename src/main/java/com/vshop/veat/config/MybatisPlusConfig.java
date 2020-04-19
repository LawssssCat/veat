package com.vshop.veat.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus的插件配置类
 *
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 19:16
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * @return 分页拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
