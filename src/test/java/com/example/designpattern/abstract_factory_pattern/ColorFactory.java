package com.example.designpattern.abstract_factory_pattern;

import com.example.designpattern.abstract_factory_pattern.abstra.AbstractFactory;
import com.example.designpattern.abstract_factory_pattern.impl.Blue;
import com.example.designpattern.abstract_factory_pattern.impl.Green;
import com.example.designpattern.abstract_factory_pattern.impl.Red;
import com.example.designpattern.abstract_factory_pattern.interf.Color;
import com.example.designpattern.abstract_factory_pattern.interf.Shape;

public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}
