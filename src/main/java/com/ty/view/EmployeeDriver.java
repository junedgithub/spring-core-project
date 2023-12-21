package com.ty.view;

import com.ty.MyConfig;
import com.ty.dao.EmployeeDao;
import com.ty.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.SplittableRandom;
@Component
public class EmployeeDriver {
    @Autowired
    static Scanner scanner;
    @Autowired
    static Employee employee;

    @Autowired
    static EmployeeDao employeeDao;

    static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        scanner = (Scanner) applicationContext.getBean("scanner");

        boolean status = true;
        do {
           System.out.println("Enter the Operation");
           System.out.println("1. Add Employee");
           System.out.println("2. Display Employee");
           System.out.println("3. Remove Employee");
           System.out.println("4. Update Employee");
           System.out.println("5. Exit");
           int choice = scanner.nextInt();
           switch (choice){
               case 1: addEmployee();
               break;
               case 2: displayEmployee();
               break;
               case 3: removeEmployee();
               break;
               case 4: updateEmployee();
               break;
               case 5: status=false;
               break;
               default:
                   System.out.println("Invalid Choice!!");
           }
       }while (status);
    }

    private static void updateEmployee() {
        System.out.println("Enter Id of Employee to be Updated");
        int id = scanner.nextInt();
        System.out.println("Enter Name to be Updated");
        String name = scanner.next();
        System.out.println("Enter the Salary to be Updated");
        double salary = scanner.nextDouble();
        employee = (Employee) applicationContext.getBean("employee");
        employee.setEmployeeName(name);
        employee.setSalary(salary);
        employeeDao = (EmployeeDao) applicationContext.getBean("employeeDao");
        boolean flag =  employeeDao.updateEmployee(employee,id);
        if (flag)
            System.out.println("Updated Successfully");
        else
            System.out.println("Not Updated");
    }

    private static void removeEmployee() {
        System.out.println("Enter Id of Employee to be Deleted!!");
        int id = scanner.nextInt();
        employeeDao = (EmployeeDao) applicationContext.getBean("employeeDao");
        boolean flag = employeeDao.removeEmployee(id);
        if (flag)
            System.out.println("Deleted Successfully");
        else
            System.out.println("Not Deleted!!");
    }

    private static void displayEmployee() {
        employeeDao = (EmployeeDao) applicationContext.getBean("employeeDao");
        List<Employee> employees = employeeDao.displayEmployees();
        for (Employee employee1: employees)
        {
            System.out.println(employee1);
        }
    }

    private static void addEmployee() {
        System.out.println("Enter Employee Name");
        String name= scanner.next();
        System.out.println("Enter Employee Department");
        String department = scanner.next();
        System.out.println("Enter Employee Salary");
        double salary = scanner.nextDouble();
        employee = (Employee)applicationContext.getBean("employee");
        employeeDao = (EmployeeDao)applicationContext.getBean("employeeDao");
        employee.setEmployeeName(name);
        employee.setDepartment(department);
        employee.setSalary(salary);
        boolean flag = employeeDao.addEmployee(employee);
        if (flag)
            System.out.println("Added Successfully!!");
        else
            System.out.println("Not Added");
    }
}
