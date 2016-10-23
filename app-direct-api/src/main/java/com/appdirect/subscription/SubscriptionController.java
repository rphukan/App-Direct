package com.appdirect.subscription;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

@RestController
public class SubscriptionController {

	@RequestMapping(path = "/subscribe", method = RequestMethod.POST)
	public void addStore() {
		try {
			System.out.println("RESPONSE : " + subscribe());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(path = "/companies", method = RequestMethod.GET)
	public void justConnect() {
		try {
			OAuthConsumer consumer = new DefaultOAuthConsumer("neareststorelocatorapi-139460", "SV6iyXwd5fAe");
			URL url = new URL("https://marketplace.appdirect.com/api/account/v1/companies");
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			consumer.sign(request);
			request.connect();
			System.out.println("RESPONSE : " + request.getResponseCode() + " " + request.getResponseMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String subscribe() throws Exception {

		String JsonResponse = null;
		String JsonDATA = "{\"company\" : { \"id\" : \"7b3966d2-a3f3-404c-bbb2-9ed61715f1bd\"}, \"user\" : { \"id\" : \"ec19aa3b-e1e4-4694-bf06-a3ae8c79bec2\"},\"paymentPlanId\" : 724,\"items\" : []}";
		BufferedReader reader = null;

		OAuthConsumer consumer = new DefaultOAuthConsumer("neareststorelocatorapi-139460", "SV6iyXwd5fAe");
		URL url = new URL("https://marketplace.appdirect.com/api/billing/v1/subscriptions");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		consumer.sign(urlConnection);
		urlConnection.setDoOutput(true);

		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setRequestProperty("Accept", "application/json");

		Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
		writer.write(JsonDATA);
		writer.close();

		InputStream inputStream = urlConnection.getInputStream();
		StringBuffer buffer = new StringBuffer();
		if (inputStream == null) {
			return null;
		}
		reader = new BufferedReader(new InputStreamReader(inputStream));

		String inputLine;
		while ((inputLine = reader.readLine()) != null) {
			buffer.append(inputLine + "\n");
		}
		if (buffer.length() == 0) {
			return null;
		}
		JsonResponse = buffer.toString();

		return JsonResponse;

	}

}
