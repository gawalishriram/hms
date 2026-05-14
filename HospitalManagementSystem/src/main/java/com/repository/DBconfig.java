package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.model.PatientModel;

public class DBconfig {

    protected Connection con;
    protected PreparedStatement pst;
    protected ResultSet rs;
    public DBconfig() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root@123");

            if (con != null) {
                System.out.println("Database connected");
            } else {
                System.out.println("Database not connected");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
}