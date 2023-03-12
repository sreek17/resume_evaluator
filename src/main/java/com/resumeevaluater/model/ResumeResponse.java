package com.resumeevaluater.model;

public class ResumeResponse {
	private String name;
	private String email;
	private String phNumber;

	public ResumeResponse() {
	}

	public ResumeResponse(String name, String lastName, String email, String phNumber) {
		super();
		this.name = name;
		this.email = email;
		this.phNumber = phNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

}
