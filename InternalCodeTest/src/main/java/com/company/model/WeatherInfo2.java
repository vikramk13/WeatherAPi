package com.company.model;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class WeatherInfo2 {
	
	private String city;
	
	private String date;
	
	private String text;

	public WeatherInfo2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeatherInfo2(String city, String date, String text) {
		super();
		this.city = city;
		this.date = date;
		this.text = text;
	}
	
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "WeatherInfo2 [city=" + city + ", date=" + date + ", text=" + text + "]";
	}



	

	
}
