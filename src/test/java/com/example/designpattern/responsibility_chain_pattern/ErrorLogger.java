package com.example.designpattern.responsibility_chain_pattern;

import com.example.designpattern.responsibility_chain_pattern.abstra.AbstractLogger;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
