package com.ty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "com.ty")
public class MyConfig {
    @Bean(name = "scanner")
    public Scanner getScanner(){
        return new Scanner(System.in);
    }
    @Bean(name = "entityManager")
    public EntityManagerFactory getEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("vikas");
    }


}
