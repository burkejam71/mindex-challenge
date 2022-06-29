package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.service.DirectReportsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DirectReportsServiceImplTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testReportCount() {
		Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");

		assertNotNull(employee);
		String employee_name = employee.getFirstName() + " " + employee.getLastName();
		// Create List to store direct reports
		List<String> direct_reports_id = new ArrayList<String>();

		// create list to store employees to check
		List<String> checked_id = new ArrayList<String>();

		checked_id.add("16a596ae-edd3-4847-99fe-c4518e82c86f");
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

		assertEquals(direct_reports_id.size(), 4);
	}
}