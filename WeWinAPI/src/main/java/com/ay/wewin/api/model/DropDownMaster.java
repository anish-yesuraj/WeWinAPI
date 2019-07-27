package com.ay.wewin.api.model;

import java.io.Serializable;

public class DropDownMaster implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String optionType;
	private String optionId;
	private String optionName;
	
	
	public DropDownMaster() {
		super();
	}

	public DropDownMaster(String optionType, String optionId, String optionName) {
		super();
		this.optionType = optionType;
		this.optionId = optionId;
		this.optionName = optionName;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	@Override
	public String toString() {
		return "DropDownMaster [optionType=" + optionType + ", optionId=" + optionId + ", optionName=" + optionName
				+ "]";
	}
	
}
