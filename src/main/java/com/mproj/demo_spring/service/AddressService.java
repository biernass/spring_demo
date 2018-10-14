package com.mproj.demo_spring.service;

import com.mproj.demo_spring.dto.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findAllAddreses();
    Optional<Address> findAddressById(Long id);
}
