package com.mproj.demo_spring.service.impl;

import com.mproj.demo_spring.dao.AddressDao;
import com.mproj.demo_spring.dto.Address;
import com.mproj.demo_spring.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressDao dataSource;

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    public AddressServiceImpl(AddressDao dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Address> findAllAddreses() {
        logger.info("findAllAddreses()");

        List<Address> result = new ArrayList<>();
        dataSource.findAll().forEach(result::add);
        //dataSource.findAll().forEach(e -> result.add(e)); to jest to samo

        return result;
    }

    @Override
    public Optional<Address> findAddressById(Long id) {
        logger.info("findAddressById() with id: {}", id);

        Optional<Address> result = dataSource.findById(id);
        result.ifPresent(address -> logger.info("Address read from dao: {}", address));

        return result;
    }

    @Override
    public void saveAddress(Address addressToSave) {
        logger.info("saveAddress() with argument: {}", addressToSave);
        dataSource.save(addressToSave);
    }

    @Override
    public void deleteAddressById(Long id) {
        logger.info("deleteAddress() with argument: {}", dataSource.findById(id));
        dataSource.deleteById(id);
    }


}
