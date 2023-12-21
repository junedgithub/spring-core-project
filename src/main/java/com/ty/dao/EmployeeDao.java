package com.ty.dao;

import com.ty.MyConfig;
import com.ty.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Component
public class EmployeeDao {
    @Autowired
    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;
    static EntityTransaction entityTransaction;
    static {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        entityManagerFactory = (EntityManagerFactory)applicationContext.getBean("entityManager");

    }

    public boolean addEmployee(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(employee);
        entityTransaction.commit();
        return true;
    }

    public boolean updateEmployee(Employee employee, int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Employee employee1 = entityManager.find(Employee.class,id);
        if (employee1!=null)
        {
            employee1.setEmployeeName(employee.getEmployeeName());
            employee1.setSalary(employee.getSalary());
            entityTransaction.begin();
            entityManager.merge(employee1);
            entityTransaction.commit();
            return true;
        }
        return false;
    }

    public boolean removeEmployee(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Employee employee = entityManager.find(Employee.class,id);
        if (employee!=null) {
            entityTransaction.begin();
            entityManager.remove(employee);
            entityTransaction.commit();
            return true;
        }
        return false;
    }

    public List<Employee> displayEmployees() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Employee e");
        List<Employee> employees = query.getResultList();
        if (employees!=null) {
            return employees;
        }
        return null;
    }

}
