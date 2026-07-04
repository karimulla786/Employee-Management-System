package com.emp;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
            	    "jdbc:mysql://localhost:3306/ems_db?useSSL=false&serverTimezone=UTC",
            	    "root",
            	    "root"
            	);
            
            if (con != null) {
                System.out.println("Connection Successful 👍");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}