package com.awsexample.assesment1.service;

import com.awsexample.assesment1.controller.EmployeeController;
import com.awsexample.assesment1.entity.Employee;
import com.awsexample.assesment1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    public void deleteEmployeeByID(Integer id){
        employeeRepository.delete(employeeRepository.getById(id));
    }

    public Employee addEmployee(Employee employee){
        Employee savedEmp = employeeRepository.save(employee);
        return savedEmp;
    }
}
