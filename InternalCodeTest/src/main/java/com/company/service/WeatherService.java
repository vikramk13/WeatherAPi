package com.company.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.company.model.Forecast;
import com.company.model.WeatherInfo;
import com.company.model.WeatherInfo2;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class WeatherService {

	public WeatherInfo info(String city) throws URISyntaxException, IOException {

		final String appId = "Byksjx54";
		final String consumerKey = "dj0yJmk9TWU0UUE0YVEwd1hTJmQ9WVdrOVFubHJjMnA0TlRRbWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTFl";
		final String consumerSecret = "564af3d910031a75a6f24de532e15191e33c29bd";
		final String url = "https://weather-ydn-yql.media.yahoo.com/forecastrss";

		long timestamp = new Date().getTime() / 1000;
		byte[] nonce = new byte[32];
		Random rand = new Random();
		rand.nextBytes(nonce);
		String oauthNonce = new String(nonce).replaceAll("\\W", "");

		List<String> parameters = new ArrayList<>();
		parameters.add("oauth_consumer_key=" + consumerKey);
		parameters.add("oauth_nonce=" + oauthNonce);
		parameters.add("oauth_signature_method=HMAC-SHA1");
		parameters.add("oauth_timestamp=" + timestamp);
		parameters.add("oauth_version=1.0");
		// Make sure value is encoded
		parameters.add("location=" + URLEncoder.encode(city, "UTF-8"));
		parameters.add("format=json");
		Collections.sort(parameters);

		StringBuffer parametersList = new StringBuffer();
		for (int i = 0; i < parameters.size(); i++) {
			parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
		}

		String signatureString = "GET&" + URLEncoder.encode(url, "UTF-8") + "&"
				+ URLEncoder.encode(parametersList.toString(), "UTF-8");

		String signature = null;

		try {
			SecretKeySpec signingKey = new SecretKeySpec((consumerSecret + "&").getBytes(), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
			Encoder encoder = Base64.getEncoder();
			signature = encoder.encodeToString(rawHMAC);

		} catch (Exception e) {
			System.err.println("Unable to append signature");
			System.exit(0);
		}

		String authorizationLine = "OAuth " + "oauth_consumer_key=\"" + consumerKey + "\", " + "oauth_nonce=\""
				+ oauthNonce + "\", " + "oauth_timestamp=\"" + timestamp + "\", "
				+ "oauth_signature_method=\"HMAC-SHA1\", " + "oauth_signature=\"" + signature + "\", "
				+ "oauth_version=\"1.0\"";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authorizationLine);
		HttpEntity<String> entity = new HttpEntity<>("paramters", headers);
		ResponseEntity<String> responseObj = restTemplate.exchange(url + "?location=" + city + "&format=json",
				HttpMethod.GET, entity, String.class);
		/* System.out.println(responseObj.getBody()); */

		/* if (responseObj != null && responseObj.getBody() != null) { */

		ObjectMapper mapper = new ObjectMapper();

		JsonFactory factory = mapper.getFactory();
		JsonParser parser1 = factory.createParser(responseObj.getBody());

		JsonNode actualObj = mapper.readTree(parser1);

		String cit = actualObj.get("location").get("city").toString();
		String weath = actualObj.get("current_observation").get("condition").get("text").toString();
		String temp = actualObj.get("current_observation").get("condition").get("temperature").toString();

		Date date = new Date(1597723200 * 1000L);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		format.setTimeZone(TimeZone.getTimeZone("pubDate"));
		String time = format.format(date);

		WeatherInfo w = new WeatherInfo();
		w.setCityName(cit);
		w.setWeatherCondition(weath);
		w.setTemparature(temp);
		w.setCurrentDate(time);

		return w;

	}

	public WeatherInfo2 info1(String city) throws URISyntaxException, IOException {

		final String appId = "Byksjx54";
		final String consumerKey = "dj0yJmk9TWU0UUE0YVEwd1hTJmQ9WVdrOVFubHJjMnA0TlRRbWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTFl";
		final String consumerSecret = "564af3d910031a75a6f24de532e15191e33c29bd";
		final String url = "https://weather-ydn-yql.media.yahoo.com/forecastrss";

		long timestamp = new Date().getTime() / 1000;
		byte[] nonce = new byte[32];
		Random rand = new Random();
		rand.nextBytes(nonce);
		String oauthNonce = new String(nonce).replaceAll("\\W", "");

		List<String> parameters = new ArrayList<>();
		parameters.add("oauth_consumer_key=" + consumerKey);
		parameters.add("oauth_nonce=" + oauthNonce);
		parameters.add("oauth_signature_method=HMAC-SHA1");
		parameters.add("oauth_timestamp=" + timestamp);
		parameters.add("oauth_version=1.0");
		// Make sure value is encoded
		parameters.add("location=" + URLEncoder.encode(city, "UTF-8"));
		parameters.add("format=json");
		Collections.sort(parameters);

		StringBuffer parametersList = new StringBuffer();
		for (int i = 0; i < parameters.size(); i++) {
			parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
		}

		String signatureString = "GET&" + URLEncoder.encode(url, "UTF-8") + "&"
				+ URLEncoder.encode(parametersList.toString(), "UTF-8");

		String signature = null;

		try {
			SecretKeySpec signingKey = new SecretKeySpec((consumerSecret + "&").getBytes(), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
			Encoder encoder = Base64.getEncoder();
			signature = encoder.encodeToString(rawHMAC);

		} catch (Exception e) {
			System.err.println("Unable to append signature");
			System.exit(0);
		}

		String authorizationLine = "OAuth " + "oauth_consumer_key=\"" + consumerKey + "\", " + "oauth_nonce=\""
				+ oauthNonce + "\", " + "oauth_timestamp=\"" + timestamp + "\", "
				+ "oauth_signature_method=\"HMAC-SHA1\", " + "oauth_signature=\"" + signature + "\", "
				+ "oauth_version=\"1.0\"";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authorizationLine);
		HttpEntity<String> entity = new HttpEntity<>("paramters", headers);
		ResponseEntity<String> responseObj = restTemplate.exchange(url + "?location=" + city + "&format=json",
				HttpMethod.GET, entity, String.class);

		ObjectMapper mapper = new ObjectMapper();

		JsonFactory factory = mapper.getFactory();
		JsonParser parser1 = factory.createParser(responseObj.getBody());

		JsonNode actualObj = mapper.readTree(parser1);

		WeatherInfo2 w = new WeatherInfo2();

		String cit = actualObj.get("location").get("city").toString();
		
		JsonNode details = actualObj.get("forecasts");
		        
		
		Iterator<JsonNode> slaidsIterator = details.elements();
		while (slaidsIterator.hasNext()) {
		    JsonNode slaidNode = slaidsIterator.next();
		   
		    

	
			    
		
	
	}
		return w;
		
		
	}

}
	/*
	 * Set<String> fields = new HashSet<>();
	 * 
	 * 
	 * 
	 * fields.add("cityName"); fields.add("weatherCondition");
	 * fields.add("Temparature");
	 * 
	 * 
	 * fields.add("chill"); fields.add("visibility"); fields.add("pressure");
	 * 
	 * FilterProvider filterProvider = new
	 * SimpleFilterProvider().addFilter("weatherfilter",
	 * SimpleBeanPropertyFilter.filterOutAllExcept(fields));
	 * 
	 * MappingJacksonValue v = new MappingJacksonValue(w);
	 * v.setFilters(filterProvider);
	 */
