package com.satria.fs.dto;

public class EmployeeRequestDto {
    private Integer employeeNumber;
    private String name;
    private String position;
    private String phoneNumber;
    private String emailAddress;

    EmployeeRequestDto() {}

    public String getEmailAddress() {
        return emailAddress;
    }
    
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
