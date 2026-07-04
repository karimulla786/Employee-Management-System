package com.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.sql.Statement;
import com.emp.model.Employee;
import com.emp.util.DBConnection;
import com.emp.exception.EmployeeNotFoundException;
import com.emp.exception.DatabaseException;
import java.sql.*;

public class EmployeeDao {
	Connection con;
	//CONSTRUCTOR
	public EmployeeDao(Connection con)
	{
		this.con=con;
	}
    //ADD EMPLOYEE
	public void addEmployee(Employee e) {

	    String sql = "INSERT INTO employee (id, name, email, phone, department, salary) VALUES (?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {

	        // Setting values
	        ps.setInt(1, e.getId());
	        ps.setString(2, e.getName());
	        ps.setString(3, e.getEmail());
	        ps.setString(4, e.getPhone());
	        ps.setString(5, e.getDepartment());
	        ps.setDouble(6, e.getSalary());

	        // Executing query
	        int rowsAffected = ps.executeUpdate();

	        // Success message
	        if (rowsAffected > 0) {

	            System.out.println("Employee added successfully.");

	        } else {

	            System.out.println("Unable to add employee. Please check the details.");

	        }

	    } catch (java.sql.SQLIntegrityConstraintViolationException ex) {

	        // Duplicate ID or unique constraint error
	        System.out.println("Error: Employee ID already exists.");

	    } catch (SQLException ex) {

	        // Other database errors
	        System.out.println("Something went wrong while accessing the database.");

	        // For developer debugging
	        ex.printStackTrace();
	    }
	}
	
    //VIEW EMPLOYEE
	public void viewEmployee() {

	    String sql = "SELECT * FROM employee";

	    try (PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        boolean found = false;

	        System.out.println("\n===== Employee Details =====");

	        while (rs.next()) {

	            found = true;

	            System.out.println("ID         : " + rs.getInt("id"));
	            System.out.println("Name       : " + rs.getString("name"));
	            System.out.println("Email      : " + rs.getString("email"));
	            System.out.println("Phone      : " + rs.getString("phone"));
	            System.out.println("Department : " + rs.getString("department"));
	            System.out.println("Salary     : " + rs.getDouble("salary"));

	            System.out.println("-----------------------------------");
	        }

	        if (!found) {

	            System.out.println("No employee records found.");

	        }

	    } catch (SQLException ex) {

	        System.out.println("Something went wrong while retrieving employee details.");

	        // For developer debugging
	        ex.printStackTrace();
	    }
	}

       
    //UPDATE EMPLOYEE
	public void updateEmployee(int id, double newSalary) {

	    String sql = "UPDATE employee SET salary = ? WHERE id = ?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {

	        // Setting values
	        ps.setDouble(1, newSalary);
	        ps.setInt(2, id);

	        // Executing query
	        int rowsAffected = ps.executeUpdate();

	        // Success message
	        if (rowsAffected > 0) {

	            System.out.println("Employee salary updated successfully.");

	        } else {

	            System.out.println("Employee not found with ID: " + id);

	        }

	    } catch (SQLException ex) {

	        System.out.println("Something went wrong while updating employee details.");

	        // For developer debugging
	        ex.printStackTrace();
	    }
	}
    //DELETE EMPLOYEE
	public void deleteEmployee(int id) {

	    String sql = "DELETE FROM employee WHERE id = ?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {

	        // Setting value
	        ps.setInt(1, id);

	        // Executing query
	        int rowsAffected = ps.executeUpdate();

	        // Success message
	        if (rowsAffected > 0) {

	            System.out.println("Employee deleted successfully.");

	        } else {

	            System.out.println("Employee not found with ID: " + id);

	        }

	    } catch (SQLException ex) {

	        System.out.println("Something went wrong while deleting employee details.");

	        // For developer debugging
	        ex.printStackTrace();
	    }
	}
	
	
    //SEARCH BY ID (WITH EXCEPTION HANDLING)
	public void getEmployeeById(int id) {

	    String sql = "SELECT * FROM employee WHERE id = ?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {

	        // Setting value
	        ps.setInt(1, id);

	        // Executing query
	        ResultSet rs = ps.executeQuery();

	        // Checking record
	        if (rs.next()) {

	            System.out.println("\n===== Employee Details Retrieved Successfully=====");

	            System.out.println("ID         : " + rs.getInt("id"));
	            System.out.println("Name       : " + rs.getString("name"));
	            System.out.println("Email      : " + rs.getString("email"));
	            System.out.println("Phone      : " + rs.getString("phone"));
	            System.out.println("Department : " + rs.getString("department"));
	            System.out.println("Salary     : " + rs.getDouble("salary"));

	        } else {

	            System.out.println("Employee not found with ID: " + id);

	        }

	    } catch (SQLException ex) {

	        System.out.println("Something went wrong while retrieving employee details.");

	        // For developer debugging
	        ex.printStackTrace();
	    }
	}
}