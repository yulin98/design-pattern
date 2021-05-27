package com.example.designpattern.future.impl;

import com.example.designpattern.future.Computer;

public class ActualComputer implements Computer {
    @Override
    public Integer compute(String id) {
        System.out.println("用户ID"+id+"正在计算");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id.hashCode();
    }
}
