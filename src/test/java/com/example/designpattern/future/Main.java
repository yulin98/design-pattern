package com.example.designpattern.future;

import com.example.designpattern.future.impl.ActualComputer;
import com.example.designpattern.future.impl.DecorateComputer;
import com.example.designpattern.future.impl.HashMapCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*ActualComputer actualComputer = new ActualComputer();
        HashMapCache hashMapCache = new HashMapCache();
        DecorateComputer decorateComputer = new DecorateComputer(hashMapCache, actualComputer);
        new Thread(()->decorateComputer.compute("jack")).start();
        new Thread(()->decorateComputer.compute("susan")).start();
        new Thread(()->decorateComputer.compute("jack")).start();*/

        Callable<Integer> call = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println("正在计算结果...");
                Thread.sleep(3000);
                return 1;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(call);

        Thread thread = new Thread(task);
        thread.start();

        // do something
        System.out.println(" 干点别的...");

        Integer result = task.get();

        System.out.println("拿到的结果为：" + result);
    }
}
