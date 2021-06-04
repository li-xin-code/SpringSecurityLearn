package com.lixin.springsecuritylearn.model.result;

import lombok.Builder;
import lombok.Data;

/**
 * 一个http响应结果通用类
 *
 * @author lx
 * @date 2021/6/4
 */
@Data
@Builder
public class HttpResult {

    /**
     * 状态码
     */
    @Builder.Default
    private Integer code = 200;

    /**
     * 消息
     */
    @Builder.Default
    private String message = "SUCCESS";

    /**
     * 数据
     */
    private Object data;


}
