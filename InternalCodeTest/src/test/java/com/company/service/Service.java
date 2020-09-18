package com.company.service;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Base64.Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.company.model.WeatherInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.model.controller.WeatherController;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class Service {

	@InjectMocks
	WeatherService service;

	@Mock
	private RestTemplate restTemplate;

	@Test
	public void test() throws IOException {
		final String appId = "Byksjx54";
		final String consumerKey = "dj0yJmk9TWU0UUE0YVEwd1hTJmQ9WVdrOVFubHJjMnA0TlRRbWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTFl";
		 final String consumerSecret = "564af3d910031a75a6f24de532e15191e33c29bd"; 
		final String url = "http://localhost:8081/getWeather/info";

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
		parameters.add("location=" + URLEncoder.encode("mumbai", "UTF-8"));
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

		ResponseEntity<WeatherInfo> w = new ResponseEntity<>(HttpStatus.ACCEPTED);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>( HttpStatus.ACCEPTED);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authorizationLine);
		HttpEntity<String> entity = new HttpEntity<>("paramters", headers);
		/*Mockito.when(restTemplate.exchange(url + "?location=" + "mumbai" + "&format=json",
				HttpMethod.GET, entity, String.class)).thenReturn(w);*/
		when(restTemplate.exchange(
		        ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<Class<WeatherInfo>>any())).thenReturn(w);
		       
				
		
		
		
		
		
		
	}
		  
		  
		 

	}



/*
 * @Test public void testingGetWeather() throws JsonParseException,
 * URISyntaxException, IOException { WeatherInfo w=new WeatherInfo();
 * w.setCityName("mumbai"); w.setCurrentDate("1597723200");
 * w.setTemparature("80"); w.setWeatherCondition("showers");
 * when(serv.info("pune")).thenReturn(w);
 * 
 */
