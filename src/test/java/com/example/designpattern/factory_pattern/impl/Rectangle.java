package com.example.designpattern.factory_pattern.impl;

import com.example.designpattern.factory_pattern.interf.Shape;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
