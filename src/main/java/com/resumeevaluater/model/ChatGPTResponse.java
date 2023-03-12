package com.resumeevaluater.model;

import java.util.List;

public class ChatGPTResponse {

	private String id;
	private String object;

	private int created;

	private String model;

	private String Usage;

	private List<Choices> choices;

	public List<Choices> getChoices() {
		return choices;
	}

	public void setChoices(List<Choices> choices) {
		this.choices = choices;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated() {
		this.created = created;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUsage() {
		return Usage;
	}

	public void setUsage(String usage) {
		Usage = usage;
	}

}
