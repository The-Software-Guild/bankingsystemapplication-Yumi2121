package com.bankingService;

import java.util.ArrayList;
import java.util.List;

import com.bankingDao.CustomerDAOImpl;
import com.bankingDao.CustomerDao;
import com.bankingDto.CustomerDTO;
import com.exception.CustomerNotFoundException;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao dao;
	
	public CustomerServiceImpl() {
		dao = new CustomerDAOImpl();
	}
	
	@Override
	public void createCustomer(CustomerDTO customer) {
		dao.create(customer);

	}

	@Override
	public void saveAllCustomers(ArrayList<CustomerDTO> customers) {
		dao.saveAll(customers);
	}
	
	@Override
	public ArrayList<CustomerDTO> getAllCustomer() {
		ArrayList<CustomerDTO> customers = dao.retrieveAll();
		return customers;
	}

	@Override
	public CustomerDTO getCustomer(int id) throws CustomerNotFoundException {
		
		CustomerDTO customer = dao.retrieve(id);
		return customer;
	}

	@Override
	public CustomerDTO getCustomer(String name) throws CustomerNotFoundException {
		CustomerDTO customer = dao.retrieve(name);
		return customer;
	}

}
