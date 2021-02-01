package com.example.designpattern.responsibility_chain_model;

import com.example.designpattern.responsibility_chain_model.abstra.AbstractLogger;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
