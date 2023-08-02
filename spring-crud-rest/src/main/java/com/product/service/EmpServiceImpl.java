package com.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.exception.ResourceNotFoundException;
import com.product.model.Employee;
import com.product.repository.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService{

	private EmpRepository empRepository;
	
	public EmpServiceImpl(EmpRepository empRepository) {
		/* super(); */
		this.empRepository = empRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		return empRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(int id) {
		/*
		 * Optional<Employee> employee=empRepository.findById(id);
		 * if(employee.isPresent()) { return employee.get(); }else { throw new
		 * ResourceNotFoundException("employee", "Id", id); }
		 */
		return empRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
	}


	@Override
	public Employee updateEmp(Employee emp, int id) {
		Employee exemployee=empRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id));
		exemployee.setFname(emp.getFname());
		exemployee.setLname(emp.getLname());
		exemployee.setEmail(emp.getEmail());
		
		empRepository.save(exemployee);
		return exemployee;
	}


	@Override
	public void deleteEmp(int id) {
		// TODO Auto-generated method stub
		empRepository.findById(id).orElseThrow(
				() ->new ResourceNotFoundException("Employee", "Id", id));
		empRepository.deleteById(id);
	}
	
	
}
