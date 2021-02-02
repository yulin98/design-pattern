package com.example.designpattern.factory_pattern.impl;

import com.example.designpattern.factory_pattern.Shape;

public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
