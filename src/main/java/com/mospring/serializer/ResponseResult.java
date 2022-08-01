package com.mospring.serializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 通用响应序列化器
 * 统一返回数据的JSON格式
 */
@ApiModel(description = "响应实体封装类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ResponseResult<T> {

    @ApiModelProperty("状态码")
    private int status;
    @ApiModelProperty("状态码描述")
    private String msg;
    @ApiModelProperty("数据实体")
    private T data;
    @ApiModelProperty("数据长度")
    private Integer total;

    /**
     * 为实体类序列化提供构造器，正常处理并返回数据
     */
    public ResponseResult(T data) {
        this.status = HttpStatus.OK.value();
        this.data = data;
        this.msg  = "success";
        if(data instanceof List){
            this.total = ((List<?>) data).size();
        }else{
            this.total = 1;
        }
    }

}
