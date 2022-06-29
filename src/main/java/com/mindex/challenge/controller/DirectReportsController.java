package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.DirectReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DirectReportsController {
	private static final Logger LOG = LoggerFactory.getLogger(DirectReportsController.class);

	@Autowired
	private DirectReportsService directReportsService;

	@GetMapping("/directreports/count/{id}")
	public String count(@PathVariable String id) {
		LOG.debug("RPS - Received employee create request for id [{}]", id);

		return directReportsService.read(id);
	}
}
