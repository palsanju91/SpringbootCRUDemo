package com.example.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

	@Autowired
	private MockMvc mvc;

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ObjectMapper objectMapper;
	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/customer/hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("hello for first controller app")));
	}
	
	@Test
	public void save() throws Exception {
		Customer c = new Customer();
		c.setId(1);
		c.setName("test");
		c.setAddress("address");
		String inputJson=objectMapper.writeValueAsString(c);
		mvc.perform(MockMvcRequestBuilders.post("/customer/").content(inputJson).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(inputJson)));
		
		

	}
	@Test
	public void getCustomeById() throws Exception {
		
		
		Customer c = new Customer();
		c.setId(1);
		c.setName("test");
		c.setAddress("address");
		String inputJson=objectMapper.writeValueAsString(c);
		mvc.perform(MockMvcRequestBuilders.get("/customer/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(inputJson)));
	}
	@Test
	public void getAllCustomers() throws Exception {
		
		List<Customer> list = new ArrayList<>();
		Customer c1 = new Customer();
		c1.setId(1);
		c1.setName("test");
		c1.setAddress("address");
		list.add(c1);	
		String inputJson=objectMapper.writeValueAsString(list);
		mvc.perform(MockMvcRequestBuilders.get("/customer/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(inputJson)));
	}
	
}
