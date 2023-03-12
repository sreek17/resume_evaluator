package com.resumeevaluater.model;

public class ResumeResponse {
	private String firstName;
	private String lastName;
	private String email;
	private String phNumber;

	public String getFirstName() {
		return firstName;
	}

	public ResumeResponse(String firstName, String lastName, String email, String phNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phNumber = phNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "ResumeResponse [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phNumber="
				+ phNumber + "]";
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
