package com.mproj.demo_spring.dao;

import com.mproj.demo_spring.dto.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PersonDao extends CrudRepository<Person, Long> {
}
