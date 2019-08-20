package com.ay.wewin.api.message.request;

import java.io.Serializable;

public class AnswerChoiceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String text;
	private String tip;
	private boolean result;
	private boolean active;
	private String imagePath;
	private String imageTip;
	private String imageSrc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImageTip() {
		return imageTip;
	}
	public void setImageTip(String imageTip) {
		this.imageTip = imageTip;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	@Override
	public String toString() {
		return "AnswerChoiceForm [id=" + id + ", text=" + text + ", tip=" + tip + ", result=" + result + ", active="
				+ active + ", imagePath=" + imagePath + ", imageTip=" + imageTip + ", imageSrc=" + imageSrc + "]";
	}
	
	

}
