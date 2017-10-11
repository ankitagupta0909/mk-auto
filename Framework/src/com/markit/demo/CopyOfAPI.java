package com.markit.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.marketxs.userxs.client.service.UserXsClient;
import com.marketxs.userxs.common.api.naming.AuthenticationException;
import com.marketxs.userxs.session.api.SessionService;
import com.marketxs.userxs.session.api.SimpleSessionRequest;
import com.marketxs.userxs.session.api.SimpleSessionRequestByEmail;
import com.marketxs.userxs.session.objects.interfaces.AccountTicket;






public class CopyOfAPI {

	public AccountTicket returnTicket() throws MalformedURLException, ServiceException, RemoteException{

		UserXsClient clientSession = new UserXsClient("https://soap.userxs-qa.markit.com");
		SessionService sessionService = clientSession.getSessionService();

		SimpleSessionRequest simpleSessionRequest = new SimpleSessionRequestByEmail(
				"Welcome@206", "Subscriber-Portal", "", "", "", "", "", -1, "", 
				true, true, false, "", null,
				"", "garima.bedi@ihsmarkit.com");

		AccountTicket ticket = sessionService.openSession2(simpleSessionRequest);
		System.out.println(ticket.getTicketId());
		System.out.println(ticket.getOwnerName());

		return ticket;


	}

	public static void main(String[] args) throws ClientProtocolException, IOException, ServiceException, URISyntaxException, org.apache.http.auth.AuthenticationException {
		
		CopyOfAPI a=new CopyOfAPI();
		try {
		
			a.authenticateandGetResponse("");
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnrecoverableKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	/*	try {
			String webPage="https://mc.stage.markit.com/api/core/clients/19000000017139/entities";
			webPage="https://api.uat.kyc.com/admin-api/matches/entities/914890093478245";
			String name = "isdass3@yahoo.in";
			String password = "markit123";
			String authString = name + ":" + password;
			System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			System.out.println("Base64 encoded auth string: " + authStringEnc);
		
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String result = sb.toString();

			System.out.println("*** BEGIN ***");
			System.out.println(result);
	

		//System.out.println(StringUtils.countMatches(result, "trueLegalName"));	
			System.out.println("*** END ***");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		
		
	}
	
	
	
/*	void authenticateandGetResponse() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException, ServiceException{
		
		API a=new API();
		AccountTicket t= a.returnTicket();
		//String authString=t.getOwnerName()+t.getTicketId();
		
		String DEFAULT_USER = t.getOwnerName();
		String DEFAULT_PASS = t.getTicketId();
		HttpHost targetHost = new HttpHost("https://api.uat.kyc.com/support-api/entities/914890093478245/cases");
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, 
		  new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS));
		 
		AuthCache authCache = new BasicAuthCache();
		authCache.put(targetHost, new BasicScheme());
		 
		//ignore certificates 
		SSLContext sslContext = new SSLContextBuilder()
	      .loadTrustMaterial(null, (certificate, authType) -> true).build();
	 
	    CloseableHttpClient client = HttpClients.custom()
	      .setSslcontext(sslContext).setHostnameVerifier(new X509HostnameVerifier() {
			
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public void verify(String paramString, String[] paramArrayOfString1,
					String[] paramArrayOfString2) throws SSLException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void verify(String paramString, X509Certificate paramX509Certificate)
					throws SSLException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void verify(String paramString, SSLSocket paramSSLSocket)
					throws IOException {
				// TODO Auto-generated method stub
				
			}
		})
	      
	      .build();
	    final HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);
		HttpClient client1 = HttpClientBuilder.create().build();
	    HttpGet httpPost = new HttpGet("https://api.uat.kyc.com/support-api/entities/914890093478245/cases");
	    httpPost.setHeader("Accept", "application/json");
	 
	    CloseableHttpResponse response = client.execute(httpPost,context);
	    ///assertThat(response.getStatusLine().getStatusCode(), 200);
	    //  = "http://api.qa.kyc.com/support-api/entities/585597556265635/cases";
	    //HttpGet getMethod = new HttpGet(urlOverHttps);
	    
	    ///
		
		
		// Add AuthCache to the execution context
		
		HttpResponse response1=null;
		try {
			response1 = client1.execute(
			  new HttpGet("https://api.uat.kyc.com/support-api/entities/914890093478245/cases"), context);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		int statusCode = response1.getStatusLine().getStatusCode();
		//assertThat(statusCode, equalTo(HttpStatus.SC_OK));
	}*/

	
void authenticateandGetResponse(String url) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException, ServiceException, org.apache.http.auth.AuthenticationException{
		
		 url="https://api.uat.kyc.com/admin-api/matches/entities/914890093478245";
		
		url="https://api.uat.kyc.com/support-api/entities/914890093478245/cases";
		CopyOfAPI a=new CopyOfAPI();
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
		HttpClient client = HttpClientBuilder.create().build();
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
		int statusCode = response1.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		
		if(statusCode==201){
			System.out.println("Pass");
		}

	}

	

}