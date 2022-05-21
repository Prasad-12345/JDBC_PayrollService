package com.bridgelabz.jdbc;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 *Author: Prasad
 *Ability to add a new Employee to the Payroll
 */
public class PayrollService {
    PayrollServiceMain payrollServiceMain = new PayrollServiceMain();
    private static PayrollService payrollService;
    List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
    int numberOfEmployee;

    /*
     * Singleton object
     */
    public static PayrollService getInstance() {
        if (payrollService == null)
            payrollService = new PayrollService();
        return payrollService;
    }

    /*
     *Method to read data from database
     */
    public List<EmployeePayrollData> readData() {
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        try (Connection connection = payrollServiceMain.getConnection()) {
            String querry = "select * from employee_payroll";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);

            while (resultSet.next()) {
                employeePayrollData.setId(resultSet.getInt(1));
                employeePayrollData.setName(resultSet.getString(2));
                employeePayrollData.setGender(resultSet.getString(3));
                employeePayrollData.setSalary(resultSet.getDouble(4));
                employeePayrollData.setDate(resultSet.getDate(5));
                employeePayrollList.add(employeePayrollData);
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " "+resultSet.getString(3) + " "+resultSet.getDouble(4) + " " + resultSet.getDate(5));
            }
            numberOfEmployee = employeePayrollList.size();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    /*
     *Method to get salary of employee
     */
    public double getEmployeeSalary(String name) {
        try (Connection connection = payrollServiceMain.getConnection()) {
            String querry = "select salary from employee_payroll where name = '" + name + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                return resultSet.getDouble("salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
     *Method to update salary of employee
     */
    public void updatePayrollService(String name, double salary) {
        try (Connection connection = payrollServiceMain.getConnection()) {
            System.out.println("querry selected");
            String querry = "update employee_payroll set salary = ? where name = ?";
            PreparedStatement statement = connection.prepareStatement(querry);
            statement.setDouble(1, salary);
            statement.setString(2, name);
            boolean resultSet = statement.execute();
            System.out.println("salary updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     *To retrieve employeepayroll with employee name
     */
    public void getEmployeePayrollWithName(String name) {
        try (Connection connection = payrollServiceMain.getConnection()) {
            String querry = "select * from employee_payroll where name = '" + name + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDouble(3) + " " + resultSet.getDate(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     *Method to retrieve data based on date
     */
    public void getEmployeePayrollWithDate() {
        try (Connection connection = payrollServiceMain.getConnection()) {
            String querry = "select * from employee_payroll where start between cast('2018-01-01' as date) and date(now());";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDouble(3) + " " + resultSet.getDate(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        /*
         *Method to add new employee to the payroll
         */
        public void addNewEmployeeToPayroll(){
            Scanner scanner = new Scanner(System.in);
            try (Connection connection = payrollServiceMain.getConnection()) {
                String querry = "insert into employee_payroll (name, gender, salary, start) values (?,?,?,?)";
                System.out.println("Enter name");
                String name = scanner.next();
                System.out.println("Enter gender");
                String gender = scanner.next();
                System.out.println("Enter salary");
                double salary = scanner.nextDouble();
                System.out.println("Enter date");
                String start = scanner.next();
                PreparedStatement statement = connection.prepareStatement(querry);
                //ResultSet resultSet = statement.executeQuery(querry);
                statement.setString(1,name);
                statement.setString(2,gender);
                statement.setDouble(3,salary);
                statement.setDate(4, Date.valueOf(start));
                int count = statement.executeUpdate();
                System.out.println("number of rows affected: " + count);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
