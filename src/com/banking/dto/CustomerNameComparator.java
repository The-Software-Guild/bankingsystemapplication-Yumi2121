package com.bankingDto;

import java.util.Comparator;
import com.bankingDto.CustomerDTO;


public class CustomerNameComparator implements Comparator<CustomerDTO> {

	@Override
	public int compare(CustomerDTO c1, CustomerDTO c2) {
		int result = c1.getName().toLowerCase().compareTo(c2.getName().toLowerCase());
		return result;
	}
}