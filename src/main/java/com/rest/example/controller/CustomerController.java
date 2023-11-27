package com.rest.example.controller;

import com.rest.example.mapper.CustomerDto;
import com.rest.example.mapper.CustomerDtoMapper;
import com.rest.example.model.Customer;
import com.rest.example.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/customer")
  public List<CustomerDto> getAllCustomers() {
    return customerService.getAllCustomers().stream()
        .map(CustomerDtoMapper::toDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer id) {
    Customer customer = customerService.getCustomerById(id);
    if (customer != null) {
      return new ResponseEntity<>(CustomerDtoMapper.toDto(customer), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/customer")
  public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
    customerService.createCustomer(customer);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
