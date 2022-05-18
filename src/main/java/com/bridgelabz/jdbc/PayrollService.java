package com.bridgelabz.jdbc;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
 *Author: Prasad
 *Create PreparedStatement to retrieve payroll data by name
 */
public class PayrollService {
    PayrollServiceMain payrollServiceMain = new PayrollServiceMain();
    private static PayrollService payrollService;

    /*
    *Singleton object
     */
    public static PayrollService getInstance() {
        if(payrollService == null)
            payrollService = new PayrollService();
        return payrollService;
    }
    /*
     *Method to read data from database
     */
    public List<EmployeePayrollData> readData() {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        try(Connection connection = payrollServiceMain.getConnection()) {
            String querry = "select * from employee_payroll";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);

            while (resultSet.next()){
                employeePayrollData.setId(resultSet.getInt(1));
                employeePayrollData.setName(resultSet.getString(2));
                employeePayrollData.setSalary(resultSet.getDouble(3));
                employeePayrollData.setDate(resultSet.getDate(4));
                employeePayrollList.add(employeePayrollData);
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDouble(3) + " " + resultSet.getDate(4));
            }
            statement.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    /*
     *Method to get salary of employee
     */
    public double getEmployeeSalary(String name){
        try(Connection connection = payrollServiceMain.getConnection()){
            String querry = "select salary from employee_payroll where name = '" + name+"'";
            Statement statement = connection.createStatement();
           // ((PreparedStatement) statement).setString(1,name);
            ResultSet resultSet = statement.executeQuery(querry);
          //  System.out.println(resultSet.getDouble(3));
           // statement.getMetaData();
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
    public void updatePayrollService(String name, double salary){
        try(Connection connection = payrollServiceMain.getConnection()){
            System.out.println("querry selected");
            String querry = "update employee_payroll set salary = ? where name = ?";
            PreparedStatement statement = connection.prepareStatement(querry);
            statement.setDouble(1,salary);
            statement.setString(2,name);
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
}
