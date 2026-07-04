package com.emp.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.emp.dao.EmployeeDao;
import com.emp.model.Employee;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Connection con = null;

        try {

            // STEP 1: CREATE DATABASE CONNECTION
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ems_db",
                    "root",
                    "root"
            );

            System.out.println("Database connected successfully.");

            // STEP 2: CREATE DAO OBJECT
            EmployeeDao dao = new EmployeeDao(con);

            // MENU LOOP
            while (true) {

                System.out.println("\n===== Employee Management System =====");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Search Employee By ID");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    // ADD EMPLOYEE
                    case 1:

                        Employee e = new Employee();

                        System.out.print("Enter ID: ");
                        e.setId(sc.nextInt());
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        e.setName(sc.nextLine());

                        System.out.print("Enter Email: ");
                        e.setEmail(sc.nextLine());

                        System.out.print("Enter Phone: ");
                        e.setPhone(sc.nextLine());

                        System.out.print("Enter Department: ");
                        e.setDepartment(sc.nextLine());

                        System.out.print("Enter Salary: ");
                        e.setSalary(sc.nextDouble());

                        // VALIDATIONS

                        // ID Validation
                        if (e.getId() <= 0) {

                            System.out.println("Invalid ID! ID must be positive.");
                            break;
                        }

                        // Name Validation
                        if (e.getName() == null || e.getName().trim().isEmpty()) {

                            System.out.println("Name cannot be empty.");
                            break;
                        }

                        // Email Validation
                        if (!e.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

                            System.out.println("Invalid email format.");
                            break;
                        }

                        // Phone Validation
                        if (!e.getPhone().matches("\\d{10}")) {

                            System.out.println("Phone number must contain exactly 10 digits.");
                            break;
                        }

                        // Department Validation
                        if (e.getDepartment() == null || e.getDepartment().trim().isEmpty()) {

                            System.out.println("Department cannot be empty.");
                            break;
                        }

                        // Salary Validation
                        if (e.getSalary() < 0) {

                            System.out.println("Salary cannot be negative.");
                            break;
                        }

                        // ADD EMPLOYEE
                        dao.addEmployee(e);
                        break;

                    // VIEW EMPLOYEES
                    case 2:

                        dao.viewEmployee();
                        break;

                    // UPDATE EMPLOYEE
                    case 3:

                        System.out.print("Enter Employee ID to update: ");
                        int uid = sc.nextInt();

                        System.out.print("Enter new Salary: ");
                        double salary = sc.nextDouble();

                        // Salary Validation
                        if (salary < 0) {

                            System.out.println("Salary cannot be negative.");
                            break;
                        }

                        dao.updateEmployee(uid, salary);
                        break;

                    // DELETE EMPLOYEE
                    case 4:

                        System.out.print("Enter Employee ID to delete: ");
                        int did = sc.nextInt();

                        dao.deleteEmployee(did);
                        break;

                    // SEARCH EMPLOYEE BY ID
                    case 5:

                        System.out.print("Enter Employee ID to search: ");
                        int sid = sc.nextInt();

                        dao.getEmployeeById(sid);
                        break;

                    // EXIT
                    case 6:

                        System.out.println("Thank you for using Employee Management System.");

                        con.close();
                        sc.close();

                        return;

                    // INVALID CHOICE
                    default:

                        System.out.println("Invalid choice! Please try again.");
                }
            }

        } catch (SQLException e) {

            System.out.println("Database connection failed.");
            e.printStackTrace();

        } catch (Exception e) {

            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
    }
}