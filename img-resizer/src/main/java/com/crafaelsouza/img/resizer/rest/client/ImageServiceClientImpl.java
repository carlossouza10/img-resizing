package com.crafaelsouza.img.resizer.rest.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceClientImpl implements ImageServiceClient {
	
	@Override
	public String retrieveImagesAsJson(String url) {
		String json = null;
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", MediaType.APPLICATION_JSON_VALUE);
		try {
			HttpResponse response = httpClient.execute(getRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			
			json = EntityUtils.toString(response.getEntity());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
