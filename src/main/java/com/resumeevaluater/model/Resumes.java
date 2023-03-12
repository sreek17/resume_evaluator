package com.resumeevaluater.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document("resumes")
public class Resumes {

	@Id
	private String id;

	private String emailId;

	private MultipartFile resume;

	private boolean flag;

	public Resumes(String emailId, MultipartFile resume) {
		super();
		this.emailId = emailId;
		this.resume = resume;
		this.flag = false;
	}

}
