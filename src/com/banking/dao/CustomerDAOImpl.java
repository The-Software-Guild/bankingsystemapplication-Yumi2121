package com.bankingDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bankingDto.CustomerDTO;
import com.exception.CustomerNotFoundException;

public class CustomerDAOImpl implements CustomerDao {

	private Connection openConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  //type-4 driver is registered with DriverManager
			System.out.println("MySQL Driver registered with DriverManager");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/custm?serverTimezone=UTC", "root", "umin1212!");
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL suitable driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
	
	private void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveAll(ArrayList<CustomerDTO> customerArr) {
		customerArr.forEach(customer -> {
			create(customer);
		});	
	}
	
	@Override
	public void create(CustomerDTO customer) {
		int customerId = customer.getCustomerId();
		String name = customer.getName();
		int age = customer.getAge();
		int mobile = customer.getMobile();
		String passportNumber = customer.getPassportNumber();
		String dateOfBirth = customer.getDateOfBirth();
		
		Connection con = openConnection();
		
		try {
			
			String sql = "INSERT INTO customer (customerId, name, age, mobile, passportNumber, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setInt(1, customerId);
			pstat.setString(2, name);
			pstat.setInt(3, age);
			pstat.setInt(4, mobile);
			pstat.setString(5, passportNumber);
			pstat.setString(6, dateOfBirth);
			
			int n = pstat.executeUpdate();
			
			System.out.println("number of rows inseted: " + n);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(con);
	}

	@Override
	public ArrayList<CustomerDTO> retrieveAll() {
		
		ArrayList<CustomerDTO> customers = new ArrayList<>();
		Connection con = openConnection();
		
		String sql = "SELECT * FROM Customer;";
		try {
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				int customerId = rs.getInt("customerId");
				String name = rs.getNString("name");
				int age = rs.getInt("age");
				int mobile = rs.getInt("mobile");
				String passportNumber = rs.getNString("passportNumber");
				String dateOfBirth = rs.getNString("dateOfBirth");
				
				CustomerDTO customer = new CustomerDTO(name, age, mobile,passportNumber, dateOfBirth);
				
				customers.add(customer);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		closeConnection(con);
		
		return customers;
	}

	@Override
	public CustomerDTO retrieve(int id) throws CustomerNotFoundException {
		Connection con = openConnection();
		CustomerDTO customer = null;
		
		String sql = "SELECT * FROM Customer WHERE id = ?;";
		try {	
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setInt(1, id);
			ResultSet rs =  pstat.executeQuery();
			
			while(rs.next()) {
				int customerId = rs.getInt("customerId");
				String name = rs.getNString("name");
				int age = rs.getInt("age");
				int mobile = rs.getInt("mobile");
				String passportNumber = rs.getNString("passportNumber");
				String dateOfBirth = rs.getNString("dateOfBirth");
				
				customer = new CustomerDTO(name, age, mobile,passportNumber, dateOfBirth);
				
			}
			
		} catch (SQLException e) {
			CustomerNotFoundException cnf = new CustomerNotFoundException("Could not find the required customer!!");
			throw cnf;	
		}
		
		closeConnection(con);
		
		return customer;
	}

	@Override
	public CustomerDTO retrieve(String name) throws CustomerNotFoundException {
		Connection con = openConnection();
		CustomerDTO customer = null;
		
		String sql = "SELECT * FROM Customer WHERE name = ?;";
		try {	
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(2, name);
			ResultSet rs =  pstat.executeQuery();
			
			while(rs.next()) {
				int customerId = rs.getInt("customerId");
				String cusName = rs.getNString("name");
				int age = rs.getInt("age");
				int mobile = rs.getInt("mobile");
				String passportNumber = rs.getNString("passportNumber");
				String dateOfBirth = rs.getNString("dateOfBirth");
				
				customer = new CustomerDTO(name, age, mobile,passportNumber, dateOfBirth);
				
			}
			
		} catch (SQLException e) {
			CustomerNotFoundException cnf = new CustomerNotFoundException("Could not find the required customer!!");
			throw cnf;	
		}
		
		closeConnection(con);
		
		return customer;
	}

}
