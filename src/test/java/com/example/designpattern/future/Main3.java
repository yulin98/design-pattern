package com.example.designpattern.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main3 {

    public static void main(String[] args) {
        Object obj = new Object();
        //消费者吃包子
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (obj) {
                        System.out.println("消费者告诉了老板包子的口味和数量");
                        System.out.println("开始等包子");
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("包子已经做好了，消费者准备开吃");
                        System.out.println("-------------------------");
                    }
                }
            }
        }.start();
        //生产者---做包子
        new Thread() {
            @Override
            public void run() {
                while (true) {

                    synchronized (obj) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("老板花5分钟做好包子并告知消费者可以吃了");
                        obj.notify();

                    }
                    //System.out.println("111111");
                }
            }
        }.start();
    }

}
