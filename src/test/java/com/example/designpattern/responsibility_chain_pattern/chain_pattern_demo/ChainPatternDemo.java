package com.example.designpattern.responsibility_chain_pattern.chain_pattern_demo;

import com.example.designpattern.responsibility_chain_pattern.abstra.AbstractLogger;
import com.example.designpattern.responsibility_chain_pattern.ConsoleLogger;
import com.example.designpattern.responsibility_chain_pattern.ErrorLogger;
import com.example.designpattern.responsibility_chain_pattern.FileLogger;

/**
 * 责任链模式测试
 */
public class ChainPatternDemo {
    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is a debug level information.");

        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
    }
}
