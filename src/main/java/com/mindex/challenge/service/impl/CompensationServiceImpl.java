package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class CompensationServiceImpl implements CompensationService {

	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee create(String employeeId, String effectiveDate, String salary) {
		// log variables for debugging purposes
		LOG.debug("Adding Compensation {}", employeeId);
		LOG.debug("Adding Compensation {}", effectiveDate);
		LOG.debug("Adding Compensation {}", salary);

		// get employee record
		Employee employee = employeeRepository.findByEmployeeId(employeeId);

		// update employee variables
		employee.setEffectiveDate(effectiveDate);
		employee.setSalary(salary);

		// save and return record
		return employeeRepository.save(employee);
	}

	@Override
	public Employee read(String id) {
		LOG.debug("Retrieving compensation for [{}]", id);
		Employee employee = employeeRepository.findByEmployeeId(id);
		if (employee == null) {
			throw new RuntimeException("Invalid employeeId: " + id);
		}

		return employee;
	}

}
