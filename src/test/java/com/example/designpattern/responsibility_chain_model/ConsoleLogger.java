package com.example.designpattern.responsibility_chain_model;

import com.example.designpattern.responsibility_chain_model.abstra.AbstractLogger;

public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
