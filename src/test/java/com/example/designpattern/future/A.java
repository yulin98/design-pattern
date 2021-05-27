package com.example.designpattern.future;

public class A {

    volatile int i = 0;

    public  int Method(String s) throws InterruptedException {



        //A线程过来获取数据
        if(s.equalsIgnoreCase("A")){
            Thread.sleep(1000);
        }

        i++;

        //B线程过来获取数据
        if(s.equalsIgnoreCase("B")){
            Thread.sleep(1000);
        }

        return i;
    }
}
