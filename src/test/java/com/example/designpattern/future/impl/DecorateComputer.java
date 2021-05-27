package com.example.designpattern.future.impl;

import com.example.designpattern.future.Cache;
import com.example.designpattern.future.Computer;

import java.util.concurrent.FutureTask;

public class DecorateComputer implements Computer {

    private Cache cache;

    private Computer computer;

    public DecorateComputer(Cache cache, Computer computer) {
        this.cache = cache;
        this.computer = computer;
    }

    @Override
    public Integer compute(String id) {
        Integer result =  cache.query(id);
        if(result == null){
            // 计算耗时的任务需要后置 防止线程重复执行计算 此时需要使用futureTask
            FutureTask<Integer> futureTask = new FutureTask<>(()->computer.compute(id));
            cache.save(id,futureTask);
            result =  cache.query(id);
        }
        System.out.println("用户ID"+id+"计算结果："+result);
        return result;
    }
}
