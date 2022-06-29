package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.DirectReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.*;
import java.util.List;

@Service
public class DirectReportsServiceImpl implements DirectReportsService {

	private static final Logger LOG = LoggerFactory.getLogger(DirectReportsServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String read(String id) {
		LOG.debug("Reporting Structure Service IMPL [{}]", id);
		Employee employee = employeeRepository.findByEmployeeId(id);
		if (employee == null) {
			throw new RuntimeException("Invalid employeeId: " + id);
		}

		String employee_name = employee.getFirstName() + " " + employee.getLastName();
		// Create List to store direct reports
		List<String> direct_reports_id = new ArrayList<String>();

		// create list to store employees to check
		List<String> checked_id = new ArrayList<String>();

		checked_id.add(id);
		List<Employee> direct_reports = new ArrayList<Employee>();
		while (true) {
			// pop employee to check off stack
			String empId = checked_id.remove(0);
			// load new employee
			employee = employeeRepository.findByEmployeeId(empId);
			// query for direct reports
			direct_reports = employee.getDirectReports();
			if (direct_reports != null) {
				for (int i = 0; i < direct_reports.size(); i++) {
					// if employee isn't on the list already ten process
					if (!checked_id.contains(direct_reports.get(i).getEmployeeId())) {
						// add employee to the lsit
						checked_id.add(direct_reports.get(i).getEmployeeId());
						// add direct reports to list if doesn't exist
						if (!direct_reports_id.contains(direct_reports.get(i).getEmployeeId())) {
							direct_reports_id.add(direct_reports.get(i).getEmployeeId());
						}
					}
				}
			}
			// break if there isn't an employeee to check
			if (checked_id.size() == 0) {
				break;
			}
		}

		String result = "The numberOfReports for employee " + employee_name + " (employeeId: " + id
				+ ") would be equal to "
				+ direct_reports_id.size() + ".";
		return result;
	}

}
