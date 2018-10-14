package com.mproj.demo_spring.controller;

import com.mproj.demo_spring.dto.Address;
import com.mproj.demo_spring.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Iterator;
import java.util.List;

@Controller
public class AddressController {

    private static Logger logger = LoggerFactory.getLogger(AddressController.class);

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/all-addresses")
    public String showAllAdresses(Model model) {

        logger.info("showAllAddreses()");

        List<Address> allAddr = addressService.findAllAddreses();
        model.addAttribute("addreses", allAddr);




        return "address/all";
    }
}
