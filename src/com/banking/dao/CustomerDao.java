package com.bankingDao;

import java.util.ArrayList;
import java.util.List;

import com.bankingDto.CustomerDTO;
import com.exception.CustomerNotFoundException;

public interface CustomerDao {
	void create(CustomerDTO customer);
	ArrayList<CustomerDTO> retrieveAll();
	void saveAll(ArrayList<CustomerDTO> customerArr);
	public CustomerDTO retrieve(int id) throws CustomerNotFoundException;
	public CustomerDTO retrieve(String name) throws CustomerNotFoundException;
	
}
