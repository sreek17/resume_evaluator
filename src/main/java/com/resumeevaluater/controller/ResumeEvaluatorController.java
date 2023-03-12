package com.resumeevaluater.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resumeevaluater.model.ResumeResponse;
import com.resumeevaluater.service.ResumeEvaluatorService;

@RestController
public class ResumeEvaluatorController {

	@Autowired
	ResumeEvaluatorService resumeEvaluatorService;

	@PostMapping("/addresume")
	public ResponseEntity<String> addResume(@RequestParam("resumes") MultipartFile[] multipartFile) throws IOException {

		// return new ResponseEntity<String>("Succesfully added 6 resumes",
		// HttpStatusCode.valueOf(200));
		return new ResponseEntity<String>(resumeEvaluatorService.addResumesToMongo(multipartFile),
				HttpStatusCode.valueOf(200));

	}

	@PostMapping("/evaluateresume")
	public List<ResumeResponse> evaluateResume(@RequestBody String jobDescription) {

		return resumeEvaluatorService.getEvaluateResumeData(jobDescription);
	}
}
