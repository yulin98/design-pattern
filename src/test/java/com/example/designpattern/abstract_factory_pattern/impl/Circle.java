package com.example.designpattern.abstract_factory_pattern.impl;

import com.example.designpattern.abstract_factory_pattern.interf.Shape;

public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
