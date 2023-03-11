package com.resumeevaluater.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resumeevaluater.model.ResumeResponse;

@RestController
public class ResumeEvaluatorController {

	@PostMapping("/addresume")
	public ResponseEntity<String> addResume(@RequestParam("resumes") MultipartFile[] multipartFile) {

		return new ResponseEntity<String>("Succesfully added 6 resumes", HttpStatusCode.valueOf(200));
	}

	@PostMapping("/evaluateresume")
	public List<ResumeResponse> evaluateResume(@RequestBody String name) {
		List<ResumeResponse> finalResponse = new ArrayList<>();

		ResumeResponse firstResponse = new ResumeResponse("Sreekar", "Pappu", "abc@d.com", "7654567654");
		ResumeResponse secondResponse = new ResumeResponse("Priyanka", "Paik", "asdas@sda.com", "9898767890");
		ResumeResponse thirdResponse = new ResumeResponse("Pathik", "Patel", "adssad@sad.com", "7673636464");
		ResumeResponse fourthResponse = new ResumeResponse("Akshay", "Jain", "aj@s.com", "73664737373");

		finalResponse.add(firstResponse);
		finalResponse.add(secondResponse);
		finalResponse.add(thirdResponse);
		finalResponse.add(fourthResponse);
		return finalResponse;
	}
}
