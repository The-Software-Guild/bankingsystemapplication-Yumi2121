package com.bankingController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.banking.Account;
import com.banking.FixedDepositAccount;
import com.banking.SavingAccount;
import com.bankingDto.CustomerDTO;
import com.bankingDto.CustomerIdComparator;
import com.bankingDto.CustomerNameComparator;
import com.bankingDto.CustomerBalanceComparator;
import com.bankingService.CustomerService;
import com.bankingService.CustomerServiceImpl;
import com.exception.CustomerNotFoundException;
import com.exception.InsufficientBalanceException;
import com.exception.InvalidDateException;
import com.exception.InvalidInputException;

public class BankingController {
	
	// case 1 function
	// -------------------------------------------------------
	public static void createNewCustomer(CustomerService service) {
		Scanner sc = new Scanner(System.in);  
		System.out.println("Please enter your name?");
		String name = sc.next();
		System.out.println("Please enter your age?");
		int age = sc.nextInt();
		System.out.println("Please enter your mobile?");
		int mobile = sc.nextInt();
		System.out.println("Please enter your passport number?");
		String passportNumber = sc.next();
		System.out.println("Enter your date of birth (dd/MM/yyyy): ");
		String dateOfBirth = sc.next();

		CustomerDTO customer = new CustomerDTO(name, age, mobile, passportNumber, dateOfBirth);
		
		// validation of DOB 
		if (validateDOB(dateOfBirth) == false) {
			System.out.println("Please enter the valid date and formmat as dd/MM/yyyy.");
			return;
		}
		service.createCustomer(customer);
		System.out.println(customer);
		System.out.println("New customer has succesfully created.");
		
	}
	
	public static void createTestCustomers(ArrayList<CustomerDTO> customerArr) {
		CustomerDTO c1 = new CustomerDTO("Lily", 10, 0123, "P12345", "06/06/2000");
		CustomerDTO c2 = new CustomerDTO("Zoe", 11, 0124, "P12346", "06/06/2000");
		CustomerDTO c3 = new CustomerDTO("Bob", 12, 0125, "P12347", "06/06/2000");
		
		System.out.println(c1.getCustomerId());
		
		customerArr.add(c1);
		customerArr.add(c2);
		customerArr.add(c3);
		
	}
	
	// DOB validation function
	public static boolean validateDOB (String dateOfBirth) {
		
		DateFormat dob = new SimpleDateFormat("dd/MM/yyyy");
		dob.setLenient(false);
		try {
			dob.parse(dateOfBirth);
		} catch (ParseException e) {
			return false;
		}
		return true;		
		
	}
	
	
	// case 2 function
	// -------------------------------------------------------
	public static void assignAccountToCustomer(CustomerService service) {
		// 1. create a new bankAccount, user enter bank account details
		Scanner sc = new Scanner(System.in);  
		
		System.out.println("Please enter your account number?");
		long accountNumber = sc.nextInt();
		System.out.println("Please enter your bsb code?");
		long bsbCode = sc.nextInt();
		System.out.println("Please enter your bank name?");
		String bankName = sc.next();
		System.out.println("Please enter your balance?");
		double balance = sc.nextInt();
		
		System.out.println("please choose saving account(1) or fixed deposit account(2)? ");
		int accountOption = sc.nextInt();
		boolean isSalaryAccount = true;
		double deposit;
		int tenure;
		Account newAccount = null;
		
		
		if (accountOption == 1) {
			while (true) {
				System.out.println("is this a salary account? y/n ");
				String salaryAccount = sc.next().toLowerCase();	
				
				
				if (salaryAccount.equals("y")) {
					isSalaryAccount = true;
					break;
				} else if (salaryAccount.equals("n")) {
					isSalaryAccount = false;
					break;
				} 
			}
			
			try {
				newAccount= new SavingAccount(accountNumber, bsbCode, bankName, balance, isSalaryAccount);
			} catch (InsufficientBalanceException e) {
				System.out.println(e.getMessage());
				return;
			}
			
			
		} else if (accountOption == 2) {
			System.out.println("How much you want to deposit? ");
			deposit= sc.nextInt();	
			System.out.println("How much years do you want keep deposit? ");
			tenure= sc.nextInt();	
			
			newAccount= new FixedDepositAccount(accountNumber, bsbCode, bankName, balance, deposit, tenure);
		} else {
			System.out.println("Please enter the given option or valid number.");
			return;
		}
		

		// 2. associate this bank account to this customer 
		System.out.println("Please enter your customer ID?");
		int customerId = sc.nextInt();

		try {
			CustomerDTO customer = service.getCustomer(customerId);
			customer.setAccount(newAccount);
			System.out.println("New bank account has succesfully added.");
			System.out.println("customer bank account is added as: " + customer.getAccount());
		} catch (Exception ex) {
			//TODO: Catch the proper exception thrown by CustomerDAOImpl
			System.out.println("Cannot find customer with the requested id");			
		}
	}
	
	
	// case 3 function
	// -------------------------------------------------------
	// will joint the Account from DB
	
	
	
	// case 4 function
	// -------------------------------------------------------
	public static void sortCustomer(CustomerService service) {
		ArrayList<CustomerDTO> customerArr = service.getAllCustomer();
		Scanner sc = new Scanner(System.in); 
		CustomerIdComparator comId = new CustomerIdComparator();
		CustomerNameComparator comName = new CustomerNameComparator();
		CustomerBalanceComparator comBalance = new CustomerBalanceComparator();
	
		while (true) {
			System.out.println("please choose sorting by: customer names(1), customer IDs(2) or bank blances(3)?");
			int accountOption = sc.nextInt();
			
			if (accountOption == 1) {
				Collections.sort(customerArr, comName);
				break;
			} else if (accountOption == 2) {
				Collections.sort(customerArr, comId);
				break;
			} else if (accountOption == 3) {
				Collections.sort(customerArr, comBalance);
				break;
			} else {
				System.out.println("Please enter the given option or valid number.");
			}
		}
	
		for(CustomerDTO customer : customerArr) {
			System.out.println(customer);
		};
	}
	

	
	// case 5 function using FileSystem
	// -------------------------------------------------------
	public static void save(CustomerService service) {
//		try {
//	        FileOutputStream fout;
//			fout = new FileOutputStream("data.txt");
//			ObjectOutputStream oout = new ObjectOutputStream(fout);
//			oout.writeObject(customerArr);
//			oout.close();
//	        fout.close();
//			System.out.println("Customer data is saved");
//		} 
//		catch (Exception e) {
//			System.out.println("Failed to save customer data");
//		}
//		service.saveAllCustomers(customerArr);
	}
	
	public static ArrayList<CustomerDTO> open() {
		try {
			FileInputStream fin;
			fin = new FileInputStream("data.txt");
			ObjectInputStream oin = new ObjectInputStream(fin);
			ArrayList<CustomerDTO> customerArr = (ArrayList<CustomerDTO>)oin.readObject();				
			oin.close();
			fin.close();
			
			return customerArr;
		}
		catch (Exception e) {
			System.out.println("Failed to load customer data. Creating a new one instead");
			return new ArrayList<CustomerDTO>();
		}
	}
	
	// case 5 function using Relational DB
	// -------------------------------------------------------
	// Everything has been installed in the database, still need to join Account with employee table
	
	
	
	// case 6 function
	// -------------------------------------------------------
	public static void showAllCustomers(CustomerService service) {
		ArrayList<CustomerDTO> customerArr = service.getAllCustomer();
		
		customerArr.forEach(customer -> {
			System.out.println(customer);
		});
	}
	
	// case 7 function 
	// -------------------------------------------------------
	public static void searchCustomersByName(CustomerService service) {
		System.out.println("Please Enter your name to begin the search");
		Scanner sc = new Scanner(System.in); 
		String name = sc.next();

		try {
			CustomerDTO customer = service.getCustomer(name);
			
			System.out.println("Customer details listed as below: " + customer);
		} catch (Exception ex) {
			System.out.println("No match customer can be found!");
		}
	}
	
	
	public void getCustomerById(int id, CustomerService service) throws CustomerNotFoundException {
		CustomerDTO customer = service.getCustomer(id);
		System.out.println(customer);
	}
	


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);  
		
		CustomerService service = new CustomerServiceImpl();
		
		// loop runs until Exit pressed
		int choice;
		do {
			System.out.println("\n ***Banking System Application***");  
			System.out.println(" 1. Create New Customer Data \n 2. Assign a Bank Account to a Customer \n 3. Display balance or interest earned of a Customer \n 4. Sort Customer Data \n 5. Persist Customer Data \n 6. Show All Customers \n 7. Search Customers by Name \n 8. Exit ");  
			System.out.println("------------------------"); 
			System.out.println("Enter your choice: ");  
			System.out.println("------------------------"); 
            
            choice = sc.nextInt();
            switch (choice) {
            	case 1:
            		createNewCustomer(service);
//            		createTestCustomers(customerArr);
            		break;
            	case 2:
            		assignAccountToCustomer(service);	
            		break;
            	case 3:
            		System.out.println("*************************"); 
            		break;
            	case 4:
        			sortCustomer(service);
            		break;
            	case 5:
        			save(service);
            		break;
            	case 6:
            		showAllCustomers(service);
            		break;
            	case 7:
            		searchCustomersByName(service);
            		break;
            	case 8:
            		System.out.println("See you! Thank you for using our banking services!");
            		break;	
            	default:
            		System.out.println("Invalid Option! Please enter again");
            		break;
            		       
            	
            }            
            
		} while (choice != 8);
		
		System.out.println("Thank you for using our banking services!");

	}

}
