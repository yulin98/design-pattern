package com.example.designpattern.factory_pattern.impl;

import com.example.designpattern.factory_pattern.interf.Shape;

public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
