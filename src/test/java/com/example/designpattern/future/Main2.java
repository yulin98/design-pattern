package com.example.designpattern.future;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.*;
import lombok.SneakyThrows;


import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();

        new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                int a1 = a.Method("A");
                System.out.println("a1:"+a1);
            }
        }.start();

//        Thread.sleep(1000);

        new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                int b1 = a.Method("B");
                System.out.println("b1:"+b1);
            }
        }.start();

    }

}
