package com.gwen.convert;

public class MarkdownItem {
	
	private String step;
	private String description;
	private String parameters;

	public MarkdownItem() {
		
	}
	
	public void setStep(String step) {
		this.step = step;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	public String toString() {
		return "Step: " + step + "\n" +
				"Description: " + description + "\n" +
				"Parameters: " + parameters;
	}

	public String getStep() {
		return step;
	}

	public String getDescription() {
		return description;
	}

	public String getParameters() {
		return parameters;
	}
}
