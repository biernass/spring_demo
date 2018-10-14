package com.mproj.demo_spring.service;

import com.mproj.demo_spring.dto.Person;

import java.util.Optional;

public interface PersonService {
    public void savePerson(Person personToSave);
    Optional<Person> findPersonById(Long personId);

}
