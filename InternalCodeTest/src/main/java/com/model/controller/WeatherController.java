package com.model.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.WeatherInfo;
import com.company.model.WeatherInfo2;
import com.company.service.WeatherService;
import com.fasterxml.jackson.core.JsonParseException;

@RestController
@RequestMapping("/getWeather")
public class WeatherController {
	
	@Autowired
	private  WeatherService serv;
	
	

	@GetMapping("/{city}")
	public WeatherInfo getInfo(@PathVariable String city) throws URISyntaxException, JsonParseException, IOException {
		
		return  serv.info(city);
	}
	
	
	@GetMapping("info/{city}")
	public WeatherInfo2 getInfo1(@PathVariable String city) throws URISyntaxException, JsonParseException, IOException {
		
		return  serv.info1(city);
	}
	
	
	
}
