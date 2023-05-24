package com.bankingService;

import java.util.ArrayList;

import com.bankingDto.CustomerDTO;
import com.exception.CustomerNotFoundException;

public interface CustomerService {
	
	void createCustomer(CustomerDTO customer);
	ArrayList<CustomerDTO> getAllCustomer();
	void saveAllCustomers(ArrayList<CustomerDTO> customers);
	CustomerDTO getCustomer(int id) throws CustomerNotFoundException;
	CustomerDTO getCustomer(String name) throws CustomerNotFoundException;
}
