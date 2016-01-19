package com.gwen.convert;

import java.util.HashMap;

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
	
	public HashMap<String, String> getParametersAsKeyValue() {
		HashMap<String, String> paramKeyValue = new HashMap<String, String>();
		String[] commas = getParameters().split(" , ");
		if (commas.length > 1) {
			for(int ii=0;ii<commas.length;ii++){
				String[] commasWithNvp = commas[ii].split("=");
				if (commasWithNvp.length == 2) {
					paramKeyValue.put(commasWithNvp[0], commasWithNvp[1]);
				}
				continue;
			}
		}
		else {
			String[] commasWithNvp = getParameters().split("=");
			if (commasWithNvp.length == 2) {
				paramKeyValue.put(commasWithNvp[0], commasWithNvp[1]);
			}
		}
		return paramKeyValue;
	}
}
