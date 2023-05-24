package com.bankingDto;

import java.io.Serializable;
import java.util.Comparator;

import com.banking.Account;


public class CustomerDTO implements Serializable, Comparable<CustomerDTO> {
	private static int latestCustomerId = 100;
	private String name;
	private int age;
	private int customerId;
	private int mobile;
	private String passportNumber;
	private Account account;
	private String dateOfBirth;
	
	public CustomerDTO() {
		
	}
	
	public CustomerDTO(String name, int age, int mobile, String passportNumber, String dateOfBirth) {
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.passportNumber = passportNumber;
		this.customerId = CustomerDTO.latestCustomerId;
		CustomerDTO.latestCustomerId += 1;
		this.dateOfBirth = dateOfBirth;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	

	public String getDateOfBirth() {		
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + ", customerId=" + customerId + ", mobile=" + mobile
				+ ", passportNumber=" + passportNumber + ", account=" + account + ", dateOfBirth=" + dateOfBirth + "]";
	}

	@Override
	public int compareTo(CustomerDTO c) {
		//sort by customerId from low to high
//		if (this.customerId < c.customerId) {
//			return -1;
//		} else if (this.customerId == c.customerId) {
//			return 0;
//		} else {
//			return 1;
//		}
		
		int result = this.name.compareTo(c.getName());
		return result;
	}
	
}
