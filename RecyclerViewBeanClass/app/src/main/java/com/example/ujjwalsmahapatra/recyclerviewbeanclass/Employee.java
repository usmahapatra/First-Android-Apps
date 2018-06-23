package com.example.ujjwalsmahapatra.recyclerviewbeanclass;

public class Employee {
    private  String name;
    private String salary;
    private String designation;

    public Employee(String name, String salary, String designation) {
        this.name = name;
        this.salary = salary;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
