package com.example.designpattern.filter_pattern.interf;

import com.example.designpattern.filter_pattern.entity.Person;

import java.util.List;

public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
