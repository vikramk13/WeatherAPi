package com.company.model;

public class Forecast {
	
	
	private String day;
	private String date;
	private String low;
	private String high;
	private String text;
	private String code;
	public Forecast() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Forecast(String day, String date, String low, String high, String text, String code) {
		super();
		this.day = day;
		this.date = date;
		this.low = low;
		this.high = high;
		this.text = text;
		this.code = code;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Forecast [day=" + day + ", date=" + date + ", low=" + low + ", high=" + high + ", text=" + text
				+ ", code=" + code + "]";
	}
	

}
