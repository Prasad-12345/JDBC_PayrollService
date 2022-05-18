package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
/*
 *Author: Prasad
 * Ability to create a payroll service database and have java program connect to database
 */
public class PayrollServiceMain {
    public static void main(String[] args) {
        //object
        PayrollServiceMain payrollServiceMain = new PayrollServiceMain();
        payrollServiceMain.getConnection();
    }

    /*
     *Method to print drivers
     */
    private static void listDrivers(){
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()){
            Driver driverClass = driverList.nextElement();
            System.out.println(driverClass.getClass().getName());
        }
    }

    /*
     *Method to get connection between java and database
     */
    public void getConnection(){
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service1";
        String uName = "root";
        String password = "Prasad@321";
        Connection connection;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        listDrivers();
        try{
            connection = DriverManager.getConnection(jdbcURL, uName, password);
            System.out.println("Connection is successful" + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
