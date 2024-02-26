package com.satria.fs.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.satria.fs.dto.EmployeeRequestDto;
import com.satria.fs.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/employee")
@Controller
public class ViewController {

    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping
    public String listEmployee(Model model, HttpServletRequest request) throws IOException {
        model.addAttribute("employees", employeeService.getEmployees(request));
        return "home";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(Model model, @PathVariable("id") Long id) throws IOException {
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "edit";
    }

    @PostMapping(value = "/edit/{id}/submit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView submitEditEmployee(@RequestBody MultiValueMap<String, String> formdata, @PathVariable("id") Long id) throws IOException {
        EmployeeRequestDto employeeRequest = new EmployeeRequestDto(
            Integer.valueOf(formdata.get("employeeNumber").get(0)),
            formdata.get("name").get(0),
            formdata.get("position").get(0),
            formdata.get("phoneNumber").get(0),
            formdata.get("emailAddress").get(0)
        );
        employeeService.putEmployee(id, employeeRequest);
        return new RedirectView("/employee/edit/" + id);
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView deleteEmployee(@RequestBody MultiValueMap<String, String> formData) throws IOException {
        employeeService.deleteEmployee(Long.valueOf(formData.get("employeeId").get(0)));
        return new RedirectView("/employee");
    }
}
