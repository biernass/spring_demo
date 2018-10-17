package com.mproj.demo_spring.controller;

import com.mproj.demo_spring.dto.Address;
import com.mproj.demo_spring.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("addresses", allAddr);

        return "address/all";
    }

    @PostMapping("/address/save")
    public String saveAddress(@ModelAttribute Address address,
                              @RequestParam(name = "pressed-button") String pushedButton) {
        logger.info("saveAddress(), ADDRESS: {}, pushedButton: {}",
                address, pushedButton);

        if ("save".equalsIgnoreCase(pushedButton)) {
            addressService.saveAddress(address);
        }
        return "redirect:/all-addresses";
    }

    @GetMapping("/address/add")
    public String addAddress(Model model) {
        logger.info("addAddres()");

            model.addAttribute("operationTitle", "New");
            model.addAttribute("mainParagraph", "Add new");
            model.addAttribute("address", new Address());


        return "address/new-edit-address";
    }

    @GetMapping("/address/delete/{id}")
    public String deleteAddress(@PathVariable Long id, Model model) {
        logger.info("deleteAddress(), id: {}", id);
        addressService.deleteAddressById(id);
        return "redirect:/all-addresses";
    }

    @GetMapping("/address/edit/{id}")
    public String editAddress(@PathVariable Long id, Model model) {
        logger.info("editAddress(), id: {}", id);

        model.addAttribute("operationTitle", "Edit");
        model.addAttribute("mainParagraph", "Edit");

        Optional<Address> addressById = addressService.findAddressById(id);
        addressById.ifPresent(address -> model.addAttribute("address", address));

        return "address/new-edit-address";
    }
}
