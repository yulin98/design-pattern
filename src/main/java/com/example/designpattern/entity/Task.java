package com.example.designpattern.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.async.DeferredResult;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task<T> {
    //延时返回对象
    private DeferredResult<String> result;

    //任务消息
    private T message;

    //是否超时
    private Boolean isTimeout;

}
