package com.resumeevaluater.model;

import java.util.List;

public class ChatGptRequest {

	private String model;

	private List<Messages> messages;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}
}
