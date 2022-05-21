package com.bridgelabz.jdbc;

import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPayrollServiceTest {
    /*
     *Test case to check updated salary of employee
     */
    @Test
    public void givenNewSalaryForEmployee_WhenRetrieved_MatchWithNewSalary(){
        PayrollService payrollService = new PayrollService();
        System.out.println("in test case");
        double salary = payrollService.getEmployeeSalary("Terisa");
        Assert.assertEquals(salary, 3000000.00);
    }

    /*
     * Test case to check new employee added to payrollservice
     */
    @Test
    public void addedNewEmployeeToPayrollService_WhenRetrieved_MatchWithListSize(){
        PayrollServiceMain payrollServiceMain = new PayrollServiceMain();
        try (Connection connection = payrollServiceMain.getConnection()) {
            String querry = "select count(*) from employee_payroll";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            resultSet.next();
            int count = resultSet.getInt(1);
            Assert.assertEquals(count,4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
