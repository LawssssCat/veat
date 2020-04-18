package com.vshop.veat.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/13 19:55
 */
@Intercepts({
        // 拦截情况的签名。当情况符合，便进行拦截。
        @Signature(
                // 指定要拦截的类型，这里拦截执行器类型
                type = Executor.class,
                // 拦截执行器中更新的方法
                method = "query",
                args = {
                        MappedStatement.class, Object.class, RowBounds.class , ResultHandler.class
                }
        )
})
@Slf4j
@Component
public class MyInterceptor implements Interceptor {

    /**
     * 这个方法是mybatis的核心方法
     * 要实现自定义逻辑，基本都是改造这个方法，即具体的业务逻辑编写在这里
     *
     * @param invocation 可以通过反射要获取原始方法和对应参数信息
     * @return 代理方法执行后的返回值。
     * @throws Throwable 可能调用任何方法，因此可能抛出任意异常
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.debug("开始拦截....");
        Object result = invocation.proceed();
        log.debug("结束拦截....");
        return result ;
    }

    /**
     * 作用是用来生成代理对象，使得被代理的对象一定会经过intercept方法
     * 通常都会使用mybatis提供的工具类Plugin来获取代理对象
     * 如：return Plugin.wrap(o, this);
     * 如果有自己独特需求，可以自定义
     *
     * @param o 被代理对象的原型。
     *          可能是：Executor、StatementHandler、ParemeterHandler、ResultHandler、甚至可以是前面四种类型的代理对象。
     * @return 对o进行封装后的代理对象，
     *          如果通过Plugin来构造代理对象：return Plugin.wrap(o, this);
     *          那么返回的是 Plugin 类型或者 原类型
     */
    @Override
    public Object plugin(Object o) {
        // 对目标对象进行包装，创建target对象的代理对象
        // 目的是将当前拦截器加入到对象中，就可以执行拦截器
        log.debug("生成代理对象");
        return Plugin.wrap(o, this);
    }

    /**
     * 就是用来设置插件的一些属性
     *
     * @param properties 解析出来的配置信息
     */
    @Override
    public void setProperties(Properties properties) {
        //Interceptor.super.setProperties(properties);//NOP
        log.debug("插件配置的信息：{}", Arrays.toString(properties.stringPropertyNames().toArray()));
    }
}
