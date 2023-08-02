package com.product.service;

import java.util.List;

import com.product.model.Employee;


public interface EmpService {

	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(int id);

	Employee updateEmp(Employee emp, int id);

	void deleteEmp(int id); 

}
