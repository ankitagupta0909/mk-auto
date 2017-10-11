package com.markit.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.xml.rpc.ServiceException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.marketxs.userxs.client.service.UserXsClient;
import com.marketxs.userxs.session.api.SessionService;
import com.marketxs.userxs.session.api.SimpleSessionRequest;
import com.marketxs.userxs.session.api.SimpleSessionRequestByEmail;
import com.marketxs.userxs.session.objects.interfaces.AccountTicket;
import com.markit.common.framework.util.ExcelUtil;

public class API {

	public AccountTicket returnTicket() throws MalformedURLException, ServiceException, RemoteException {

		UserXsClient clientSession = new UserXsClient("https://soap.userxs-qa.markit.com");
		SessionService sessionService = clientSession.getSessionService();
		SimpleSessionRequest simpleSessionRequest = new SimpleSessionRequestByEmail("Welcome@207", "Subscriber-Portal",
				"", "", "", "", "", -1, "", true, true, false, "", null, "", "garima.bedi@ihsmarkit.com");
		AccountTicket ticket = sessionService.openSession2(simpleSessionRequest);
		System.out.println(ticket.getTicketId());
		System.out.println(ticket.getOwnerName());
		return ticket;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, ServiceException,
	URISyntaxException, org.apache.http.auth.AuthenticationException, JSONException, KeyManagementException,
	UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, Exception {
		API a = new API();
		try {

			String token = a.generateToken();
			//a.getSDLAPI(token);
			a.tc_calTimestamp(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void authenticateandGetResponse(String url)
			throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
			ClientProtocolException, IOException, ServiceException, org.apache.http.auth.AuthenticationException {

		// url="https://api.uat.kyc.com/admin-api/matches/entities/914890093478245";

		url = "https://api.uat.kyc.com/support-api/entities/914890093478245/cases";
		API a = new API();
		AccountTicket t = a.returnTicket();
		String DEFAULT_USER = t.getOwnerName();
		String DEFAULT_PASS = t.getTicketId();
		HttpHost targetHost = new HttpHost(url);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS));

		AuthCache authCache = new BasicAuthCache();
		authCache.put(targetHost, new BasicScheme());

		final HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);
		// HttpClient client = HttpClientBuilder.create().build();
		HttpClient client = acceptUntrustedCerts();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", "application/x-www-form-urlencoded");
		/*
		 * List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 * urlParameters.add(new BasicNameValuePair("matchList",
		 * "919856137428606")); post.setEntity(new
		 * UrlEncodedFormEntity(urlParameters));
		 */

		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS);
		Header header = new BasicScheme().authenticate(credentials, post, context);
		post.addHeader(header);

		HttpResponse response1 = null;
		try {
			response1 = client.execute(post);
			System.out.println(response1);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = 0;
		try {
			statusCode = response1.getStatusLine().getStatusCode();
			System.out.println(statusCode);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		if (statusCode == 201) {
			System.out.println("Pass");
		}
	}

	public HttpClient acceptUntrustedCerts()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		HttpClientBuilder b = HttpClientBuilder.create();
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] arg0, String arg1) {
				return true;
			}
		}).build();
		b.setSslcontext(sslContext);
		HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
				(X509HostnameVerifier) hostnameVerifier);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory)
				.build();
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		b.setConnectionManager(connMgr);
		HttpClient client = b.build();
		return client;
	}
	// Install the all-trusting trust manager

	public String generateToken()
			throws ClientProtocolException, IOException, InterruptedException, JSONException, Exception {
		String url = "https://data.qa.kyc.com/oauth/token";
		HttpClient client = acceptUntrustedCerts();
		HttpPost post = new HttpPost(url);
		String authorization = Base64.encodeBase64String("sdlapi_3@markit.com:Welcome88#".getBytes());// ;//("api2@merc.com:Welcome@12".getBytes());
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");// ("Content-Type",
		// P3);
		post.setHeader("Authorization", "Basic " + authorization);
		// set parameters
		ArrayList<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
		urlParameters.add(new BasicNameValuePair("grant_type", "client_credentials"));// ("grant_type","client_credentials"));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);

		Thread.sleep(5000);
		System.out.println("Sending POST request to URL : " + url);
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

	void getSDLAPI(String token) throws ClientProtocolException, IOException, InterruptedException, JSONException,
	KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

		String url = "https://data.qa.kyc.com/ms-rs-data/sdl/relationships/65000002644297";	
		HttpClient client = acceptUntrustedCerts();
		HttpGet get = new HttpGet(url);

		// add request header
		get.addHeader("Authorization", "Bearer " + token);
		HttpResponse response = client.execute(get);
		Thread.sleep(6000);
		System.out.println("\n Sending 'GET' request to URL :" + url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result);

		Map<String, Object> relationMap=readAPIResponse(result);
		List<String> keys=readAPIKeysFromExcel("src/testdata/SDL_TestData.xlsx", "GeneralReg");
		writeAPIResponseInExcel(relationMap, keys,"src/testdata/SDL_TestData.xlsx", "GeneralReg");
	
	}

	void tc_calTimestamp(String token) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, IOException, InterruptedException, JSONException{
		getSDLTimestampAPI(token,"2017-06-05","");
	}
	void getSDLTimestampAPI(String token,String timestamp,String next) throws ClientProtocolException, IOException, InterruptedException, JSONException,
	KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

		String url="https://data.qa.kyc.com/ms-rs-data/sdl/relationships?fromTimestamp="+timestamp+"&next="+next;
		HttpClient client = acceptUntrustedCerts();
		HttpGet get = new HttpGet(url);

		// add request header
		get.addHeader("Authorization", "Bearer " + token);
		HttpResponse response = client.execute(get);
		Thread.sleep(6000);
		System.out.println("\n Sending 'GET' request to URL :" + url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result);

		JSONObject root = new JSONObject(result.toString().trim());		
		
		int countPerPage=0;
		JSONArray rowssArray=root.getJSONArray("relationshipList");
		for(int rowNo=0;rowNo<rowssArray.length();rowNo++)
	      {
				JSONObject RespArray = rowssArray.getJSONObject(rowNo);
				String relId=RespArray.getString("relationshipId");
				if(relId!=null){
					countPerPage++;
				}
	      }		
		if(countPerPage==100){
			System.out.println("pas"+countPerPage+ "records");	
			}
		else{
			
		}
		String ts=null;
		String nxt=null;
		try{
			JSONObject nextReqObj= root.getJSONObject("nextRequest");
			nxt= nextReqObj.getString("next");
			ts = nextReqObj.getString("fromTimestamp");
		}catch(Exception e){
			
		}		
			
		if(ts!=null && nxt!=null){
		getSDLTimestampAPI(token,ts,nxt);
		}
	}

	public Map<String, Object> readAPIResponse(StringBuffer result) throws JSONException{
		JSONObject root = new JSONObject(result.toString().trim());
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(root != JSONObject.NULL) {
			retMap = toMap(root);
		}
		Map<String,Object> relationMap = (Map<String,Object>) ((List<Object>)retMap.get("relationshipList")).get(0);
		return relationMap;
	}

	public Map<String, Object> readAPINextRequest(StringBuffer result) throws JSONException{
		JSONObject root = new JSONObject(result.toString().trim());
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(root != JSONObject.NULL) {
			retMap = toMap(root);
		}
		Map<String,Object> relationMap = (Map<String,Object>) ((List<Object>)retMap.get("nextRequest")).get(0);
		return relationMap;
	}

	public void writeAPIResponseInExcel(Map<String, Object> relationMap,List<String> keys,String wbPath,String sheetName){	    
		for(String key:keys){
			String value=returnAPIValueByKey(key, relationMap);
			writeValueForAPIKey(wbPath, sheetName, key, value);
		}		
	}

	public String returnAPIValueByKey(String key,Map<String,Object> relationMap){
		String[] keyList = key.split(":");
		for(int i=0;i<keyList.length-1;i++){
			Object o = relationMap.get(keyList[i]);
			if(o instanceof List){
				List<Object> ll = (List<Object>) o;
				relationMap = (Map<String,Object>)ll.get(0);		    		
			}else{
				relationMap = (Map<String,Object>) o;
			}
		}
		if(relationMap.get(keyList[keyList.length-1]).toString()!=null)
			return relationMap.get(keyList[keyList.length-1]).toString();
		else return "Value not found in API Response";
	}

	public List<String> readAPIKeysFromExcel(String wbPath,String sheetName){	
		Workbook wbObj=ExcelUtil.getWorkBookObject(wbPath);
		Sheet wbSheet=wbObj.getSheet(sheetName);
		List<String> apiKeyList = new ArrayList<String>();
		for (int i=1;i<=wbSheet.getLastRowNum();i++){
			String value=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "Value", i);
			if(value!=null){
				String api_key=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "API_Key", i);
				try{
					apiKeyList.add(api_key);
				}catch(NullPointerException e){e.printStackTrace();}
			}
		}
		return apiKeyList;
	}

	public  void writeValueForAPIKey(String testDataPath,String sheetName,String KeyToUpdate,String setValue){
		Workbook wb=	ExcelUtil.getWorkBookObject(testDataPath);
		Sheet sheetObj=wb.getSheet(sheetName);
		Row fstRowObj=sheetObj.getRow(0);
		int colNo=ExcelUtil.getColumnNumberByColumnName("API_Key", fstRowObj);
		int rowNo=ExcelUtil.getRowNumberByColName(sheetObj, colNo, KeyToUpdate);	
		ExcelUtil.setColumnDataByColName(testDataPath, sheetName, "API_Value", rowNo, setValue);
	}
	/*	
	public void compareTimestamp(){
		   final SimpleDateFormat dateFmt = new SimpleDateFormat(
                   "2017-01-24T11:22:35.504Z");
		   final SimpleDateFormat dateFmt1 = new SimpleDateFormat(
                   "2017-01-24T11:22:35.600Z");

		String date1 = "2017-01-24T11:22:35.600Z";
		if(dateFmt1.>dateFmt){}
	}*/

	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	// working method
	public void parse(JSONObject json, StringBuilder sb, String prefix, Map<String, String> map)
			throws IOException, JSONException {

		Iterator<String> keys = json.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			String val = null;
			try {
				JSONObject value = json.getJSONObject(key);
				sb.append(key + "_");
				parse(value, sb, key, map);
			} catch (Exception e) {
				val = json.getString(key);
			}
			if (val != null && val.charAt(0) != '[') {
				System.out.println(key);
				System.out.println(val);
				map.put(sb + key, val);
				// out.put(prefix,val);
			}
			if (val != null && val.charAt(0) == '[') {
				JSONArray jsonArray = new JSONArray(val);
				for (int arrEl = 0; arrEl < jsonArray.length(); arrEl++) {
					JSONObject obj = jsonArray.optJSONObject(arrEl);
					if (obj != null) {
						sb.append(key + "_");
						parse(obj, sb, key, map);

					}
				}
			} // callwrite excel
		}
		sb.delete(0, sb.length());
	}
}
