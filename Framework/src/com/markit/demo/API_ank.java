package com.markit.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.xml.rpc.ServiceException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

import com.google.gson.JsonObject;
import com.marketxs.userxs.client.service.UserXsClient;
import com.marketxs.userxs.session.api.SessionService;
import com.marketxs.userxs.session.api.SimpleSessionRequest;
import com.marketxs.userxs.session.api.SimpleSessionRequestByEmail;
import com.marketxs.userxs.session.objects.interfaces.AccountTicket;
import com.sun.codemodel.JCodeModel;

public class API_ank {

	
	JSONObject root;
	Map<String[],String> mainMap=new HashMap<String[],String>();
	public AccountTicket returnTicket() throws MalformedURLException, ServiceException, RemoteException{

		UserXsClient clientSession = new UserXsClient("https://soap.userxs-qa.markit.com");
		SessionService sessionService = clientSession.getSessionService();
		SimpleSessionRequest simpleSessionRequest = new SimpleSessionRequestByEmail(
				"Welcome@207", "Subscriber-Portal", "", "", "", "", "", -1, "", 
				true, true, false, "", null,
				"", "garima.bedi@ihsmarkit.com");
		AccountTicket ticket = sessionService.openSession2(simpleSessionRequest);
		System.out.println(ticket.getTicketId());
		System.out.println(ticket.getOwnerName());
		return ticket;
	}


	public static void main(String[] args) throws ClientProtocolException, IOException, ServiceException, URISyntaxException, org.apache.http.auth.AuthenticationException, JSONException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException ,Exception{		
		API_ank a=new API_ank();
		//testNewAPI();
			//a.returnTicket();
		try {
			
			String token=a.generateToken();		

				//a.generateToken();
		   a.getSDLAPI(token);
		//	a.authenticateandGetResponse("");

			//        a.getSDLAPI(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*try {	
			a.authenticateandGetResponse("");
		} catch (KeyManagementException e1) {
			e1.printStackTrace();
		} catch (UnrecoverableKeyException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}		*/
	}




	private static void testNewAPI() throws IOException {
		JCodeModel codeModel = new JCodeModel();

		URL source = new URL("file:C:\\Users\\ankita.gupta\\Desktop\\ankita\\schema.json");

		GenerationConfig  config = new DefaultGenerationConfig() {
		@Override
		public boolean isGenerateBuilders() { // set config option by overriding method
		return true;
		}
		};

		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
		mapper.generate(codeModel, "ClassName", "com.example", source);

		codeModel.build(new File("c:\\jsom"));
		
	}


	void authenticateandGetResponse(String url) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException, ServiceException, org.apache.http.auth.AuthenticationException{

		//url="https://api.uat.kyc.com/admin-api/matches/entities/914890093478245";

		url="https://api.uat.kyc.com/support-api/entities/914890093478245/cases";
		API_ank a=new API_ank();
		AccountTicket t= a.returnTicket();	
		String DEFAULT_USER = t.getOwnerName();
		String DEFAULT_PASS = t.getTicketId();
		HttpHost targetHost = new HttpHost(url);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, 
				new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS));

		AuthCache authCache = new BasicAuthCache();
		authCache.put(targetHost, new BasicScheme());

		final HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);
	//	HttpClient client = HttpClientBuilder.create().build();
		HttpClient client=acceptUntrustedCerts();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", "application/x-www-form-urlencoded");
		/*	    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(new BasicNameValuePair("matchList", "919856137428606"));
	    post.setEntity(new UrlEncodedFormEntity(urlParameters));*/

		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS);
		Header header=new BasicScheme().authenticate(credentials, post, context);
		post.addHeader(header);

		HttpResponse response1=null;
		try {
			response1 = client.execute(post);
			System.out.println(response1);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		 
		
		int statusCode =0;
		try{
		statusCode = response1.getStatusLine().getStatusCode();
		System.out.println(statusCode);
	
		}
		catch(NullPointerException e){
		e.printStackTrace();
		}

		if(statusCode==201){
			System.out.println("Pass");
		}
	}

	
	public HttpClient acceptUntrustedCerts() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		 HttpClientBuilder b = HttpClientBuilder.create();
		    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
		        public boolean isTrusted(X509Certificate[] arg0, String arg1) {
		            return true;
		        }
		    }).build();
		    b.setSslcontext( sslContext);
		    HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		    SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, (X509HostnameVerifier) hostnameVerifier);
		    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
		            .register("http", PlainConnectionSocketFactory.getSocketFactory())
		            .register("https", sslSocketFactory)
		            .build();
		    PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager( socketFactoryRegistry);
		    b.setConnectionManager( connMgr);
		    HttpClient client = b.build();
		    return client;
	}
	// Install the all-trusting trust manager


	public String generateToken() throws ClientProtocolException, IOException, InterruptedException, JSONException, Exception{
		String url = "https://data.qa.kyc.com/oauth/token";
		//String url = "http://data.dev.kyc.com/oauth/token"; //dev
		
		//String url = "https://data.uat.kyc.com/oauth/token";
		HttpClient client =acceptUntrustedCerts();
		HttpPost post = new HttpPost(url);
		String authorization = Base64.encodeBase64String("sdlapi_3@markit.com:Welcome88#".getBytes());//;//("api2@merc.com:Welcome@12".getBytes());
		//String authorization = Base64.encodeBase64String("JPChaseISDA2@api2.com:47K7ck%$".getBytes());//;//("api2@merc.com:Welcome@12".getBytes());
		//String authorization = Base64.encodeBase64String("kycsupport_Citi@kyc.com:Qwer@1234".getBytes());// dev
		//add header
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");//("Content-Type", P3);
		post.setHeader("Authorization","Basic "+authorization);
		//set parameters
		ArrayList<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
		urlParameters.add(new BasicNameValuePair("grant_type","client_credentials"));//("grant_type","client_credentials"));
		//set encondedurl
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		//execute post request
		
		HttpResponse response = client.execute(post);

		Thread.sleep(5000);
		System.out.println("Sending POST request to URL : " + url);
		System.out.println("POST parameters : " + post.getEntity());
		System.out.println("Response code :" + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) 
		{
			result.append(line);
		}

		System.out.println(result);
		JSONObject root = new JSONObject(result.toString());
		String res=root.getString("access_token").toString();
		return res;
	}


	
	
	
	void getSDLAPI(String token) throws ClientProtocolException, IOException, InterruptedException, JSONException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException{


		//String url = "https://data.qa.kyc.com/ms-rs-data/sdl/relationships?fromTimestamp=2017-02-03";
		String url = "https://data.qa.kyc.com/ms-rs-data/sdl/relationships/40000000001083";
//65000001909406
		
	//	String url = "https://data.uat.kyc.com/ms-rs-data/sdl/relationships/31000000744572";
		HttpClient client = acceptUntrustedCerts();
		HttpGet get = new HttpGet(url);

		//add request header
		get.addHeader("Authorization","Bearer "+token);
		HttpResponse response = client.execute(get);
		Thread.sleep(6000);
		System.out.println("\n Sending 'GET' request to URL :" + url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader (new InputStreamReader (response.getEntity().getContent()));
		StringBuffer result = new StringBuffer ();
		String line ="";
		while ((line= rd.readLine()) != null)
		{
			result.append(line);
		}
		System.out.println(result);
		/* try {
	readJsonResponse(result);
} catch (WriteException | JSONException e) {
	e.printStackTrace();
}*/
		JSONObject root = new JSONObject(result.toString().trim());
	//	String res1=root.getString("relationshipList").toString();  
		Map<String, String> out = new LinkedHashMap<String, String>();
		StringBuilder sb=new StringBuilder("");
		createPathMap(root);
		parse(root,sb,out);
		
		System.out.println(out);
	/*	HashMap<String,String> myKeyValues = new HashMap<String,String>();
		Stack<String> key_path = new Stack<String>();
        loadJson(root, key_path, myKeyValues);*/
		//new File("input.json")
		
	}

	/*static void readJsonResponse(String response) throws IOException, JSONException, RowsExceededException, WriteException{
	String mstr="C:\\Test\\OutputFile\\JsonResponseMCPM"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date(0)).replace(".", "")+".xls";
	File Jsonfile = new File(mstr);//new File("D:\\Training\\testing.xls");
    WritableWorkbook jsonkbook = Workbook.createWorkbook(Jsonfile);
    jsonkbook.createSheet("MCPM Exported data", 0);
    WritableSheet jsonsheet = jsonkbook.getSheet(0);

	JSONObject root = new JSONObject(response.toString());
    JSONArray rowssArray = root.getJSONArray("");
      // now get the first element:
    System.out.println(rowssArray.length());
    for(int jr=0;jr<rowssArray.length();jr++)
      {
			JSONObject RespArray = rowssArray.getJSONObject(jr);
			int jsnRoElm=0;
			String[] elementHeaderNames = JSONObject.getNames(RespArray);
			for (String elementName : elementHeaderNames)
		    {
				if(jr<1)
				{
					Label JsnLeadContact = new Label(jsnRoElm, jr, elementName);
					jsonsheet.addCell(JsnLeadContact);
					jsnRoElm=jsnRoElm+1;
				}
		    }
			jsnRoElm=0;
			String[] elementNames = JSONObject.getNames(RespArray);
			for (String elementName : elementNames)
		    {
		        String value = RespArray.getString(elementName);
		        System.out.printf("name=%s, value=%s\n", elementName, value);
		        Label JsnLeadContact = new Label(jsnRoElm, jr+1, value);
				jsonsheet.addCell(JsnLeadContact);
				jsnRoElm=jsnRoElm+1;
		    }
      }
      jsonkbook.write();
      jsonkbook.close();
}*/



	private void createPathMap(JSONObject root) {
		Map<String[],String> map=new HashMap<String[],String>();
		Iterator<String> keys=root.keys();
		while(keys.hasNext()){
			map.put(root.getNames(keys), keys.next());
			
		}
		
	}


	static void readJsonResponse(StringBuffer result) throws IOException, JSONException, RowsExceededException, WriteException{
		String mstr="C:\\Test\\OutputFile\\JsonResponseMCPM"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date(0)).replace(".", "")+".xls";
		File Jsonfile = new File(mstr);//new File("D:\\Training\\testing.xls");
		WritableWorkbook wb = Workbook.createWorkbook(Jsonfile);
		wb.createSheet("MCPM Exported data", 0);
		WritableSheet sheet = wb.getSheet(0);
		System.out.println("response to string" + result.toString());

		System.out.println("response to string" + result.toString().trim().charAt(0));
		JSONObject root = new JSONObject(result.toString().trim());
		//String res=root.getString("nextRequest").toString();
		//String res1=root.getString("relationshipList").toString();

		JSONArray rowssArray=root.getJSONArray("relationshipList");
		/*for(int rowNo=0;rowNo<rowssArray.length();rowNo++)
      {
			JSONObject RespArray = rowssArray.getJSONObject(rowNo);
			int colNo=0;
			String[] elementHeaderNames = JSONObject.getNames(RespArray);
			for (String elementName : elementHeaderNames)
		    {
				if(rowNo<1)
				{
					Label label = new Label(colNo, rowNo, elementName);
					sheet.addCell(label);
					colNo=colNo+1;
				}

		    }
			colNo=0;
			String[] elementNames = JSONObject.getNames(RespArray);
			for (String elementName : elementNames)
		    {
		        String value = RespArray.getString(elementName);
		        System.out.printf("name=%s, value=%s\n", elementName, value);
		        Label label = new Label(colNo, rowNo+1, value);
		        sheet.addCell(label);
				colNo=colNo+1;
		    }
      }*/

		wb.createSheet("cptySubmissionList", 1);
		sheet = wb.getSheet(1);

		/*	   JSONObject obj=rowssArray.toJSONObject(rowssArray);
	   System.out.println(obj.keys());
	   while(obj.keys().hasNext()){
		   System.out.println(obj.keys().next().toString());

	   }*/
		JSONObject ob=(JSONObject) rowssArray.get(0);	 
		JSONArray rowssArraySubList=ob.getJSONArray("cptySubmissionList");
		/*  for(int rowNo=0;rowNo<rowssArraySubList.length();rowNo++)
      {
			JSONObject RespArray = rowssArraySubList.getJSONObject(rowNo);
			int colNo=0;
			String[] elementHeaderNames = JSONObject.getNames(RespArray);
			for (String elementName : elementHeaderNames)
		    {
				if(rowNo<1)
				{
					Label label = new Label(colNo, rowNo, elementName);
					sheet.addCell(label);
					colNo=colNo+1;
				}

		    }
			colNo=0;
			String[] elementNames = JSONObject.getNames(RespArray);
			for (String elementName : elementNames)
		    {
		        String value = RespArray.getString(elementName);
		        System.out.printf("name=%s, value=%s\n", elementName, value);
		        Label label = new Label(colNo, rowNo+1, value);
		        sheet.addCell(label);
				colNo=colNo+1;
		    }			
      }*/

		JSONObject newOb=(JSONObject) rowssArraySubList.get(0);		
		newOb= newOb.getJSONObject("EU");
		JSONObject mainOb=newOb.getJSONObject("euAANA");
		String[] elementNamesTotal = JSONObject.getNames(newOb);

		/*	  String[] elementNames = JSONObject.getNames(newOb);
	  for (String elementName : elementNames)
	    {
	        String value = newOb.getString(elementName);
	        System.out.println(value);
	    }			
		 */
		for(int rowNo=0;rowNo<elementNamesTotal.length;rowNo++)
		{
			//JSONObject RespArray = ar.getJSONObject(rowNo);
			int colNo=0;
			//String[] elementHeaderNames = JSONObject.getNames(RespArray);
			String[] elementHeaderNames = JSONObject.getNames(mainOb);
			for (String elementName : elementHeaderNames)
			{
				if(rowNo<1)
				{
					Label label = new Label(colNo, rowNo, elementName);
					sheet.addCell(label);
					colNo=colNo+1;
				}

			}
			colNo=0;
			//String[] elementNames = JSONObject.getNames(RespArray);
			String[] elementNames = JSONObject.getNames(mainOb);
			for (String elementName : elementNames)
			{
				String value = mainOb.getString(elementName);
				System.out.printf("name=%s, value=%s\n", elementName, value);
				Label label = new Label(colNo, rowNo+1, value);
				sheet.addCell(label);
				colNo=colNo+1;
			}			
		}



		wb.write();
		wb.close();
	}
	
//working method
	public  void parse(JSONObject json,StringBuilder sb,Map<String,String> map) throws IOException, JSONException  {
		Iterator<String> keys = json.keys();
		while(keys.hasNext()){
			String key = keys.next();
		
			//String key = keys.next();
			String val = null;			
				Object newJson=json.get(key) ;
				sb.append(key+"_");
				if(newJson instanceof JSONArray){
					JSONArray jsonArray=new JSONArray(newJson.toString());
					if(jsonArray!=null && jsonArray.length()>0){
					for(int arrEl=0;arrEl<jsonArray.length();arrEl++){
					JSONObject obj=jsonArray.optJSONObject(arrEl);			
					if(obj!=null){		
						parse(obj,sb,map);		}
					}
				}else{
					sb.delete(sb.lastIndexOf(key), sb.lastIndexOf("_"));
				}}
				else if(newJson instanceof JSONObject){
					JSONObject value = new JSONObject(newJson.toString());				
					parse(value,sb,map);
				}else {
					mainMap.put(json.getNames(key), key);
					System.out.println(key);
					System.out.println("before "+sb.toString());
					map.put(sb.toString(),newJson.toString());
					sb.delete(sb.lastIndexOf(key), sb.lastIndexOf("_"));
					sb.deleteCharAt(sb.lastIndexOf("_"));
					System.out.println("after "+sb.toString());
					//sb=new StringBuilder();
				}}
				
					
	}	
	
	
	public void verifyKeyisJSONArray(String val,StringBuilder sb, Map<String,String> map,String key) throws JSONException, IOException{
		if(val != null && val.charAt(0)!='['){
			System.out.println(key);
			System.out.println(val);
			map.put(sb+"_"+key,val);
			//out.put(prefix,val);  
		}if(val!=null && val.charAt(0)=='['){				
			//sb.delete(0, sb.length());
		}
	}


	private void getJSONObjectFromJSONArraysandParse(String val,
			StringBuilder sb, Map<String, String> map) throws JSONException,
			IOException {
		JSONArray jsonArray=new JSONArray(val);
		for(int arrEl=0;arrEl<jsonArray.length();arrEl++){
		JSONObject obj=jsonArray.optJSONObject(arrEl);			
		if(obj!=null){		
			//sb.append(key+"_");
			parse(obj,sb,map);				
		}
		}
	}
	private boolean checkIfJsonArray(String json){
		if(json!=null && json.charAt(0)=='['){
			return true;
		}
		return false;
	}
	
	private void loadJson(JSONObject json,Stack<String> key_path,Map<String,String> myKeyValues) throws JSONException{

	    Iterator<?> json_keys = json.keys();

	    while( json_keys.hasNext() ){
	        String json_key = (String)json_keys.next();

	        try{
	        	key_path.push(json_key);
	            loadJson(json.getJSONObject(json_key), key_path, myKeyValues);
	       }catch (JSONException e){
	           // Build the path to the key
	           String key = "";
	           for(String sub_key: key_path){
	               key += sub_key+".";
	           }
	           key = key.substring(0,key.length()-1);

	           System.out.println(key+": "+json.getString(json_key));
	           key_path.pop();
	           myKeyValues.put(key, json.getString(json_key));
	        }
	    }
	    if(key_path.size() > 0){
	        key_path.pop();
	    }
	}

	
	///////////This is the original method
/*	public  void parseOriginal(JSONObject json,StringBuilder sb,String prefix,Map<String,String> out) throws IOException, JSONException  {

		Iterator<String> keys = json.keys();
		while(keys.hasNext()){
			String key = keys.next();
			String val = null;
           

			try{
				JSONObject value = json.getJSONObject(key);				
				sb.append(key+"_");
				parse(value,sb,key,out);
				//sb.delete(0, sb.length());

			}catch(Exception e){
				val = json.getString(key);
				
			}

			if(val != null && val.charAt(0)!='['){
				System.out.println(key);
				System.out.println(val);
				out.put(sb+key,val);      

				//out.put(prefix,val);  
			}
			
			if(val!=null && val.charAt(0)=='['){				
				JSONArray jsonArray=new JSONArray(val);
				for(int arrEl=0;arrEl<jsonArray.length();arrEl++){
				JSONObject obj=jsonArray.optJSONObject(arrEl);			
				if(obj!=null){		
					sb.append(key+"_");
					parse(obj,sb,key,out);
		
				}
			    }
			}//callwrite excel
		 }
		sb.delete(0, sb.length());
	}	*/
	

}
