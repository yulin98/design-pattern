package com.example.designpattern.abstract_factory_pattern.impl;

import com.example.designpattern.abstract_factory_pattern.interf.Shape;

public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
