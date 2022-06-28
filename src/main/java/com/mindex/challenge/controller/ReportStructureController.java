package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ReportStructureController {
	private static final Logger LOG = LoggerFactory.getLogger(ReportStructureController.class);

	@Autowired
	private ReportingStructureService reportingStructureService;

	@GetMapping("/reportingstructure/number_of_reports/{id}")
	public String count(@PathVariable String id) {
		LOG.debug("RPS - Received employee create request for id [{}]", id);

		return reportingStructureService.read(id);
	}
}
