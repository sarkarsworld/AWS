package com.awsexample.assesment1.controller;

import com.awsexample.assesment1.entity.Employee;
import com.awsexample.assesment1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        Employee addEmployee = employeeService.addEmployee(employee);
        return addEmployee;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeService.deleteEmployeeByID(id);
        return "Employee deleted with id="+id;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

}
