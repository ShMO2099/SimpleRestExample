package com.rest.example.service;

import com.rest.example.model.Customer;
import com.rest.example.repositories.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer getCustomerById(Integer id) {
    return customerRepository.findById(id).orElse(null);
  }

  public void createCustomer(Customer customer) {
      customerRepository.save(customer);
  }

}
