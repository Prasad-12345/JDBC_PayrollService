package com.bridgelabz.jdbc;

import org.testng.Assert;
import org.testng.annotations.Test;

public class JDBCPayrollServiceTest {
    /*
     *Test case to check updated salary of employee
     */
    @Test
    public void givenNewSalaryForEmployee_WhenRetrieved_MatchWithNewSalary(){
        System.out.println("in test case");
        PayrollService payrollService = new PayrollService();
        double salary = payrollService.getEmployeeSalary("Terisa");
        Assert.assertEquals(salary, 3000000.00);
    }
}
