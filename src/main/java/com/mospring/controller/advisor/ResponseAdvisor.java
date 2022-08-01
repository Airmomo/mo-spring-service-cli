package com.mospring.controller.advisor;


import com.mospring.serializer.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ResponseAdvisor：实现了ResponseBodyAdvice接口，重写supports跟beforeBodyWrite方法
 * 基于ControllerAdvice和HttpMessageConverter实现对Controller层返回值的统一拦截和封装
 *
 * 需要注意的是：
 * ControllerAdvice默认会对当前项目的所有接口的返回数据做统一的拦截并处理，
 * 由于swagger接口中返回的数据也被拦截和处理，所以当打开swagger地址http://localhost:8080/swagger-ui/index.html时，
 * 会出现swagger接口返回的数据格式和期望的格式不一致的问题，导致swagger报错
 *
 * 解决办法：
 * 配置ControllerAdvice或RestControllerAdvice的注解中的basePackages属性来限制其生效的包，
 * 只有被扫描的包下Controller才会被拦截处理，从而使得swagger不受影响。
 */
@ControllerAdvice(basePackages = "com.mospring.controller")
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (o instanceof ResponseResult) {
            return o;
        }
        return new ResponseResult<>(o);
    }

}
