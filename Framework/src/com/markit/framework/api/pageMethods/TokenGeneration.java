package com.markit.framework.api.pageMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.markit.common.framework.util.Config;

public class TokenGeneration {

	public  Map<String,String> apiConfig=Config.loadConfig("src/testdata/API.properties");	

	public String generateToken(String url,String user,String password) throws ClientProtocolException, IOException, InterruptedException, JSONException{

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(apiConfig.get(url));
		String userNamePswd=apiConfig.get(user)+":"+apiConfig.get(password);
		String authorization = Base64.encodeBase64String(userNamePswd.getBytes());
		//add header
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("Authorization","Basic "+authorization);
		//set parameters
		ArrayList<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
		urlParameters.add(new BasicNameValuePair("grant_type","client_credentials"));
		//set encondedurl
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		//execute post request
		HttpResponse response = client.execute(post);

		System.out.println("POST parameters : " + post.getEntity());
		System.out.println("Response code :" + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result);
		JSONObject root = new JSONObject(result.toString());
		String res = root.getString("access_token").toString();
		return res;
	}
}