package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;

import java.util.List;

public interface CompensationService {

	Employee create(String employeeId, String effectiveDate, String salary);

	Employee read(String id);

}
