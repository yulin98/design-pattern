package com.example.designpattern.future.impl;

import com.example.designpattern.future.Cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class HashMapCache implements Cache {

    private final static ConcurrentHashMap<String, Future<Integer>> cache = new ConcurrentHashMap<>();

    @Override
    public Integer query(String key) {
        try {
            Future<Integer> integerFuture = cache.get(key);
            if(integerFuture == null){
                return null;
            }
            return integerFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(String key, FutureTask<Integer> value) {
        Future<Integer> integerFuture = cache.putIfAbsent(key, value);
        // 如果有值了 则不再计算  防止重复计算
        if(integerFuture == null){
            value.run();
        }
        // 非原子操作，最终还是会重复计算
//        cache.put(key,value);
//        value.run();
    }
}
