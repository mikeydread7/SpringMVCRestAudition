package com.mike.audition.model;

public class LookUpForm {
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public LookUpForm() {
		super();
	}

	public LookUpForm(boolean result, String id) {
		super();
		this.result = result;
		this.id = id;
	}


	private boolean result;
    private String id;
}
