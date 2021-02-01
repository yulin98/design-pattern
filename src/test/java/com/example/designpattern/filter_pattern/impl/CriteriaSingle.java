package com.example.designpattern.filter_pattern.impl;

import com.example.designpattern.filter_pattern.Criteria;
import com.example.designpattern.filter_pattern.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
