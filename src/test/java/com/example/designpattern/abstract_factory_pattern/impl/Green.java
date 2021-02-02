package com.example.designpattern.abstract_factory_pattern.impl;

import com.example.designpattern.abstract_factory_pattern.interf.Color;

public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
