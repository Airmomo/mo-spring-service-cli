package com.mospring.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

import java.util.List;

/**
 *
 * 全局响应封装配置
 *
 * 在项目中，Controller类的返回值没有String类型的，都是包装成ResponseResult来返回数据。
 * 如果需要String类型的返回值，就有可能遇到类型不匹配的问题。
 *
 * 根本原因：
 * HttpMessageConverter是根据Controller的原始返回值类型进行处理的，
 * 而我们在ResponseAdvisor中改变了返回值的类型，都包装成ResponseResult来返回数据。
 * 如果HttpMessageConverter处理的目标类型是Object还好说，如果是其它类型就会报错，其中最容易出现问题的就是String类型，
 * 因为在所有的HttpMessageConverter实例集合中，StringHttpMessageConverter要比其它的Converter排得靠前一些。
 *
 * 解决办法：
 * 将处理Object类型的HttpMessageConverter放得靠前一些，这里使用在一个Configuration类实现：
 */
@Configuration
public class ResponseResultConfiguration extends DelegatingWebMvcConfiguration {

    /**
     * 添加返回数据的处理器（默认只支持JackSon）
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //converters.add(0, new MappingJackson2HttpMessageConverter());
        converters.add(0, new FastJsonHttpMessageConverter());//添加FastJSON转换器并设置其优先级最高
        super.configureMessageConverters(converters);
    }

}
