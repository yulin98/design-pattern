package com.example.designpattern.responsibility_chain_pattern;

import com.example.designpattern.responsibility_chain_pattern.abstra.AbstractLogger;

public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
