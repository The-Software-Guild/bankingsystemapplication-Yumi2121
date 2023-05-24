package com.bankingDto;

import java.util.Comparator;
import com.bankingDto.CustomerDTO;


public class CustomerBalanceComparator implements Comparator<CustomerDTO> {

	@Override
	public int compare(CustomerDTO c1, CustomerDTO c2) {
		if (c1.getAccount().getBalance() < c2.getAccount().getBalance()) {
			return 1;
		} else if (c1.getAccount().getBalance() == c2.getAccount().getBalance()) {
			return 0;
		} else {
		return -1;
		}
	}
}