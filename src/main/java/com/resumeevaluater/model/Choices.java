package com.resumeevaluater.model;

public class Choices {

	private int index;

	private String finish_reason;

	private Messages message;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getFinish_reason(String stop) {
		return finish_reason;
	}

	public void setFinish_reason(String finish_reason) {
		this.finish_reason = finish_reason;
	}

	public Messages getMessage() {
		return message;
	}

	public void setMessage(Messages message) {
		this.message = message;
	}

	public String getFinish_reason() {
		return finish_reason;
	}

}
