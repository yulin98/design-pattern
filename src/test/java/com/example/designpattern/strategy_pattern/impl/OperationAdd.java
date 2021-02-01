package com.example.designpattern.strategy_pattern.impl;

import com.example.designpattern.strategy_pattern.Strategy;

public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
