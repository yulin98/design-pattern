package com.example.designpattern.abstract_factory_pattern.impl;

import com.example.designpattern.abstract_factory_pattern.interf.Color;

public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
