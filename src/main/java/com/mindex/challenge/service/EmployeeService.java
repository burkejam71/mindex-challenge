package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    Employee read(String id);

    Employee update(Employee employee);

    /* CREATE A SERVICE TO LIST ALL EMPLOYEES */
    List list();
}
