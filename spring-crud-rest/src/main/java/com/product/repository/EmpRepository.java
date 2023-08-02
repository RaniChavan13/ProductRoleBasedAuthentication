package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer>{

}
