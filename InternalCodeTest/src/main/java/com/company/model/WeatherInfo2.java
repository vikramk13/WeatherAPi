package com.company.model;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class WeatherInfo2 {
	
	private String city;
	
	private List<JsonNode> date;
	
	private List<JsonNode> weather;
	

	public WeatherInfo2() {
		super();
		// TODO Auto-generated constructor stub
	}


	public WeatherInfo2(String city, List<JsonNode> date, List<JsonNode> weather) {
		super();
		this.city = city;
		this.date = date;
		this.weather = weather;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public List<JsonNode> getDate() {
		return date;
	}


	public void setDate(List<JsonNode> date) {
		this.date = date;
	}


	public List<JsonNode> getWeather() {
		return weather;
	}


	public void setWeather(List<JsonNode> weather) {
		this.weather = weather;
	}


	@Override
	public String toString() {
		return "WeatherInfo2 [city=" + city + ", date=" + date + ", weather=" + weather + "]";
	}

	
}
