package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

/*
 *Author: Prasad
 *Create PreparedStatement to retrieve payroll data by name
 */
public class PayrollServiceMain {
    public static void main(String[] args) {
        //object
        PayrollServiceMain payrollServiceMain = new PayrollServiceMain();
        payrollServiceMain.getConnection();
        payrollServiceMain.getMenu();
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
    public Connection getConnection(){
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
       // return connection;
        return connection;
    }

    public void getMenu(){
        //object
        PayrollService payrollService = new PayrollService();
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        do{
            System.out.println("1.To read database\n2.Update Salary\n3.Retrieve payroll data with name\n4.exit");
            System.out.println("Enter choice");
            int choice = scanner.nextInt();
            switch (choice){
                case 1 :
                    payrollService.readData();
                    break;

                case 2 :
                    payrollService.updatePayrollService("Terisa", 3000000.00);
                    break;

                case 3:
                    payrollService.getEmployeePayrollWithName("Terisa");
                    break;

                case 4:
                    check = false;
                    break;

                default:
                    System.out.println("Enter valid choice");
            }
        } while (check);
    }
}
