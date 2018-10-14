package com.mproj.demo_spring.controller;


import com.mproj.demo_spring.dto.Address;
import com.mproj.demo_spring.dto.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestController {

    @GetMapping("/person-as-rest")
    public Person getPerson(){

        Address address = new Address("warsaw", "somestreet");
        Person person = new Person("Janek", "Kowalczyk");
        person.setAddress(address);

        return person;
    }

}
