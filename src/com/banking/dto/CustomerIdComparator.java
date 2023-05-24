package com.bankingDto;

import java.util.Comparator;
import com.bankingDto.CustomerDTO;


public class CustomerIdComparator implements Comparator<CustomerDTO> {

	@Override
	public int compare(CustomerDTO c1, CustomerDTO c2) {
		if (c1.getCustomerId() < c2.getCustomerId()) {
			return -1;
		} else if (c1.getCustomerId() == c2.getCustomerId()) {
			return 0;
		} else {
		return 1;
		}
	}
}
