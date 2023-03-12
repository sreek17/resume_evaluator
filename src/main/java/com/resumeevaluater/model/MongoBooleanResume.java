package com.resumeevaluater.model;

public class MongoBooleanResume {

	public MongoBooleanResume(String emailId, boolean flag) {
		super();
		this.emailId = emailId;
		this.flag = flag;
	}

	private String emailId;

	private boolean flag;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
