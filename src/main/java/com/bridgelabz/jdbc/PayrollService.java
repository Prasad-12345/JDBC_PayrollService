package com.bridgelabz.jdbc;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
 *Author: Prasad
 *Ability to update the salary i.e. the base pay for Employee Terisa to 3000000.00 and sync it with Database
 */
public class PayrollService {
    PayrollServiceMain payrollServiceMain = new PayrollServiceMain();
    /*
     *Method to read data from database
     */
    public List<EmployeePayrollData> readData() {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        try(Connection connection = payrollServiceMain.getConnection()) {
            String querry = "select * from employee_payroll";
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(querry);

            while (resultset.next()){
                employeePayrollData.setId(resultset.getInt(1));
                employeePayrollData.setName(resultset.getString(2));
                employeePayrollData.setSalary(resultset.getDouble(3));
                employeePayrollData.setDate(resultset.getDate(4));
                employeePayrollList.add(employeePayrollData);
                System.out.println(resultset.getInt(1) + " " + resultset.getString(2) + " " + resultset.getDouble(3) + " " + resultset.getDate(4));
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
}
