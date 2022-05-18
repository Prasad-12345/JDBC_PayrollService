package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

/*
 *Author: Prasad
 *Ability for Employee Payroll Service to retrieve the Employee Payroll from the Database
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
        Connection connection = null;
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

        //object
        PayrollService payrollService = new PayrollService();
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        do{
            System.out.println("1.To read database\n2.exit");
            System.out.println("Enter choice");
            int choice = scanner.nextInt();
            switch (choice){
                case 1 :
                    payrollService.readData(connection);
                    break;

                case 2:
                    check = false;
                    break;

                default:
                    System.out.println("Enter valid choice");
            }
        } while (check);
    }
}
