package com.example.designpattern.abstract_factory_pattern.abstra;

import com.example.designpattern.abstract_factory_pattern.interf.Color;
import com.example.designpattern.abstract_factory_pattern.interf.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape) ;
}
