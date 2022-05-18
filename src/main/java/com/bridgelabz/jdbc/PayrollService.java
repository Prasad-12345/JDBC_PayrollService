package com.bridgelabz.jdbc;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 *Author: Prasad
 * Ability for Employee Payroll Service to retrieve the Employee Payroll from the Database
 */
public class PayrollService {
    /*
     *Method to read data from database
     */
    public List<EmployeePayrollData> readData(Connection connection) {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        try {
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
}
