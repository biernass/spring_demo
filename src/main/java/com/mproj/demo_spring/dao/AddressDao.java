package com.mproj.demo_spring.dao;

import com.mproj.demo_spring.dto.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressDao extends CrudRepository<Address, Long> {
}
