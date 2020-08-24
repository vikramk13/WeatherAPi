package com.company.model;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter(value = "weatherfilter")
public class WeatherInfo {

	private String cityName;
	private String currentDate;
	private String weatherCondition;
	private String Temparature;
	
	public WeatherInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeatherInfo(String cityName, String currentDate, String weatherCondition, String temparature) {
		super();
		this.cityName = cityName;
		this.currentDate = currentDate;
		this.weatherCondition = weatherCondition;
		Temparature = temparature;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}

	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

	public String getTemparature() {
		return Temparature;
	}

	public void setTemparature(String temparature) {
		Temparature = temparature;
	}

	@Override
	public String toString() {
		return "WeatherInfo [cityName=" + cityName + ", currentDate=" + currentDate + ", weatherCondition="
				+ weatherCondition + ", Temparature=" + Temparature + "]";
	}

	

	
	
}
