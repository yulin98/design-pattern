package com.example.designpattern.filter_pattern;

import com.example.designpattern.filter_pattern.entity.Person;

import java.util.List;

public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
