package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.List;

@RestController
public class CompensationController {
	private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

	@Autowired
	private CompensationService compensationService;

	@PostMapping("/compensation")
	public Employee create(@RequestParam("employeeId") String employeeId,
			@RequestParam("effectiveDate") String effectiveDate, @RequestParam("salary") String salary) {

		return compensationService.create(employeeId, effectiveDate, salary);
	}

	@GetMapping("/compensation/{id}")
	public Employee read(@PathVariable String id) {
		LOG.debug("Compensation for id [{}]", id);

		return compensationService.read(id);
	}
}
