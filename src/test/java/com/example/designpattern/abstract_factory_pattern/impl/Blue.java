package com.example.designpattern.abstract_factory_pattern.impl;

import com.example.designpattern.abstract_factory_pattern.interf.Color;

public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
