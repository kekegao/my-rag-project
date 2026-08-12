package com.example.demo.dto;

import lombok.Data;

@Data
public class EmployeeInfo {
    private String name;
    private String department;
    private String position;
    private String entryDate;

    public EmployeeInfo(String name, String department, String position, String entryDate) {
        this.name = name;
        this.department = department;
        this.position = position;
        this.entryDate = entryDate;
    }
}
