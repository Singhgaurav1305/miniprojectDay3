package com.example.ems.controller;

import com.example.ems.model.Employee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

  private List<Employee> employees = new ArrayList<>();
  private int nextId = 1;

  @GetMapping
  public List<Employee> getAllEmployees() {
    return employees;
  }

  @PostMapping
  public Employee createEmployee(@RequestBody Employee employee) {
    employee.setId(nextId++);
    employees.add(employee);
    return employee;
  }

  @PutMapping("/{id}")
  public Employee updateEmployee(@PathVariable int id,
                                 @RequestBody Employee updatedEmployee) {
    for (Employee e : employees) {
      if (e.getId() == id) {
        e.setName(updatedEmployee.getName());
        e.setDepartment(updatedEmployee.getDepartment());
        e.setSalary(updatedEmployee.getSalary());
        return e;
      }
    }
    return null;
  }

  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable int id) {
    employees.removeIf(e -> e.getId() == id);
  }
}
