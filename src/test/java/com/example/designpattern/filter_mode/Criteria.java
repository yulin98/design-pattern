package com.example.designpattern.filter_mode;

import com.example.designpattern.filter_mode.entity.Person;

import java.util.List;

public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
