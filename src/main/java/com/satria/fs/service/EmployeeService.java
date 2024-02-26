package com.satria.fs.service;

import java.io.IOException;
import java.util.List;

import com.satria.fs.model.Employee;
import com.satria.fs.dto.EmployeeRequestDto;

import jakarta.servlet.http.HttpServletRequest;

public interface EmployeeService {
    String postEmployee(EmployeeRequestDto employeeRequest) throws IOException;
    String putEmployee(Long id, EmployeeRequestDto employeeRequest) throws IOException;
    void deleteEmployee(Long id)  throws IOException;
    List<Employee> getEmployees(HttpServletRequest httpRequest) throws IOException;
    Employee getEmployee(Long id) throws IOException;
}
