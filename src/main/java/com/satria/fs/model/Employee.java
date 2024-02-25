package com.satria.fs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Employee
 */
@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seq_employee", sequenceName = "seq_employee", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private Integer employeeNumber;

    @Column(length = 128)
    private String name;

    private String position;
    private String phoneNumber;
    private String emailAddress;
    private Boolean isActive = true;

    Employee() {}

    public String getEmailAddress() {
        return emailAddress;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public Long getId() {
        return id;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Employee(
        Integer employeeNumber,
        String name,
        String position,
        String phoneNumber,
        String emailAddress,
        Boolean isActive
    ) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.isActive = isActive;
    }
}