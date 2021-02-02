package com.example.designpattern.abstract_factory_pattern;

import com.example.designpattern.abstract_factory_pattern.abstra.AbstractFactory;
import com.example.designpattern.abstract_factory_pattern.impl.Circle;
import com.example.designpattern.abstract_factory_pattern.impl.Rectangle;
import com.example.designpattern.abstract_factory_pattern.impl.Square;
import com.example.designpattern.abstract_factory_pattern.interf.Color;
import com.example.designpattern.abstract_factory_pattern.interf.Shape;

public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
