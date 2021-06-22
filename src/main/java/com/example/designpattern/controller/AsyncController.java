package com.example.designpattern.controller;

import com.example.designpattern.entity.Task;
import com.example.designpattern.until.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
@Slf4j
public class AsyncController {

    //Callable处理异步
    @RequestMapping("/order")
    public Callable<String> order()throws Exception{
        log.info("主线程开始");
        Callable<String> result = ()->{
            log.info("子线程开启");
            Thread.sleep(5000L);
            log.info("子线程结束");
            return "success";
        };
        log.info("主线程返回");
        return result;
    }

    @Autowired
    private MockQueue mockQueue;

    //异步测试
    @RequestMapping("/test")
    public DeferredResult<String> test(Long timeout) throws InterruptedException{
        log.info("/test 收到请求");

        //新建延期返回对象并设置超时时间，优先级比configreAsyncSupport方法中默认配置中的高
        DeferredResult<String> result = new DeferredResult<>(timeout);

        //要执行的任务
        Task<String> task = new Task<>(result, "test任务", false);

        //设置超时后执行的任务，优先级比DeferredResultProccessingInterceptor拦截器中的高
        result.onTimeout(()->{
            log.info("任务超时");
            task.setIsTimeout(true);
            result.setErrorResult("任务超时");
        });

        //任务入队
        mockQueue.put(task);

        return result;
    }

}
