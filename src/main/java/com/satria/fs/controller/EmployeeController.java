package com.satria.fs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satria.fs.dto.EmployeeRequestDto;
import com.satria.fs.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @PostMapping
    public ResponseEntity<Object> postEmployee (@RequestBody EmployeeRequestDto requestEmployee) throws IOException {
        String errorDesc = employeeService.postEmployee(requestEmployee);
        if (errorDesc != null)
            return new ResponseEntity<>(errorDesc, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Object> getEmployee(HttpServletRequest httpRequest) throws IOException {
        return new ResponseEntity<>(employeeService.getEmployees(httpRequest), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> putEmployee(
        @PathVariable String id,
        @RequestBody EmployeeRequestDto employeeRequest
    ) throws IOException {
        String errorDesc = employeeService.putEmployee(Long.valueOf(id), employeeRequest);
        if (errorDesc != null)
            return new ResponseEntity<>(errorDesc, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String id) throws IOException {
        employeeService.deleteEmployee(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
