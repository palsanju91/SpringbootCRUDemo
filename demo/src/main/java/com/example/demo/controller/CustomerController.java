package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	

	@Autowired
	CustomerRepository customerRepository;
	//Added logger
		private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/")
	public Customer saveCustomer(@RequestBody Customer customer )
	{
		return customerRepository.save(customer);
	}
	
	@GetMapping("/")
	public List<Customer> getCustomers()
	{
		List<Customer> l=customerRepository.findAll();
		return l;
	}
	
	@GetMapping("/{customernumber}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customernumber)
			throws ResourceNotFoundException {
		LOG.info("Getting Customer with ID {}.", customernumber);
		Customer c = customerRepository.findById(customernumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not present for id : " + customernumber));
		return ResponseEntity.ok().body(c);
	}
	
	@GetMapping("/hello")
	public String sayHello()
	{
		return "hello for first controller app";
	}
	
	
}
