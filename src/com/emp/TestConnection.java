package com.emp;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ems_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASSWORD");

            Connection con = DriverManager.getConnection(url, user, pass);

            if (con != null) {
                System.out.println("Connection Successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
