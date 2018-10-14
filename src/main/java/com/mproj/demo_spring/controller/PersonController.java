package com.mproj.demo_spring.controller;

import com.mproj.demo_spring.dao.AddressDao;
import com.mproj.demo_spring.dao.PersonDao;
import com.mproj.demo_spring.dto.Address;
import com.mproj.demo_spring.dto.Person;
import com.mproj.demo_spring.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);


    private PersonService personService;


    private static List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person("Michal", "B.", new Address("Warsaw", "Maja")));
        personList.add(new Person("Adam", "W.", new Address("Cracow", "Komorowska")));
        personList.add(new Person("Maciej", "A.", new Address("Warsaw", "Marszalkowska")));
        personList.add(new Person("Kamil", "D.", new Address("Wroclaw", "Radzyminska")));
        personList.add(new Person("Anna", "R.", new Address("Warsaw", "Smolenska")));
    }

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public String showPersonData(Model model) {
        logger.info("showPersonData()");

        Address address = new Address("Warsaw", "JakasUlica");
        Person personToSave = new Person("Jan", "Kowalski");

        personToSave.setAddress(address);

        logger.info("Person before save: {}", personToSave);
        personService.savePerson(personToSave);
        logger.info("Person after save: {}", personToSave);

        Optional<Person> maybePerson = personService.findPersonById(personToSave.getId());
        maybePerson.ifPresent(person -> {
            logger.info("Person read from db: {}", person);
            model.addAttribute("data", person);
        });

        //  Person me = new Person("Michal", "B.", new Address("Warsaw", "Komorowska"));
        //  model.addAttribute("data", me);
        return "persons/person";
    }

    @GetMapping("/persons")
    public String showListOfPerson(Model model) {
        model.addAttribute("persons", personList);
        return "persons/personList";

    }


}
