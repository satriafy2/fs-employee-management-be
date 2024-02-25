package com.satria.fs.service;

import java.io.IOException;

import com.satria.fs.dto.GetEmployeesResponseDto;
import com.satria.fs.dto.EmployeeRequestDto;

import jakarta.servlet.http.HttpServletRequest;

public interface EmployeeService {
    String postEmployee(EmployeeRequestDto employeeRequest) throws IOException;
    String putEmployee(Long id, EmployeeRequestDto employeeRequest) throws IOException;
    void deleteEmployee(Long id)  throws IOException;
    GetEmployeesResponseDto getEmployees(HttpServletRequest httpRequest) throws IOException;
}
