package com.markit.framework.api.pageMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.xml.rpc.ServiceException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.marketxs.userxs.client.service.UserXsClient;
import com.marketxs.userxs.common.api.naming.AuthenticationException;
import com.marketxs.userxs.session.api.SessionService;
import com.marketxs.userxs.session.api.SimpleSessionRequest;
import com.marketxs.userxs.session.api.SimpleSessionRequestByEmail;
import com.marketxs.userxs.session.objects.interfaces.AccountTicket;
import com.markit.common.framework.util.ReportUtil;

public class KYCAPI {

public void authenticateandGetResponse_EntityMerge(String url, String kycEntId, String mcpmEntId) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException, ServiceException, org.apache.http.auth.AuthenticationException{

		url=url+kycEntId;
		
		Authentication au=new Authentication();
		AccountTicket t= au.returnTicket();	
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
		//HttpClient client = HttpClientBuilder.create().build();
		HttpClient client=au.acceptUntrustedCerts();
	    HttpPost post = new HttpPost(url);
	    post.setHeader("Content-type", "application/x-www-form-urlencoded");
	   
	    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(new BasicNameValuePair("matchList", mcpmEntId));
	    post.setEntity(new UrlEncodedFormEntity(urlParameters));
	    
	    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS);
	    Header header=new BasicScheme().authenticate(credentials, post, context);
	    post.addHeader(header);
	    
		HttpResponse response=null;
		try {
			response = client.execute(post);
			System.out.println(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		 
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		
		if(statusCode==201 || statusCode==200){
			System.out.println("Pass");
			ReportUtil.reportWebElement("Entity merge API successfull", true);
		}else{ReportUtil.reportWebElement("Entity merge API Failed", false);}

	}


public void authenticateandGetResponse_CaseCreate(String url, String kycEntId) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException, ServiceException, org.apache.http.auth.AuthenticationException{
			
	    url=url+kycEntId+"/cases";
		Authentication au=new Authentication();
		AccountTicket t= au.returnTicket();	
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
		//HttpClient client = HttpClientBuilder.create().build();
		HttpClient client=au.acceptUntrustedCerts();
	    HttpPost post = new HttpPost(url);
	    post.setHeader("Content-type", "application/x-www-form-urlencoded");
	    
	    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS);
	    Header header=new BasicScheme().authenticate(credentials, post, context);
	    post.addHeader(header);
	    
		HttpResponse response=null;
		try {
			response = client.execute(post);
			System.out.println(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		 
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		
		if(statusCode==201 || statusCode==200){
			System.out.println("Pass");
			ReportUtil.reportWebElement("Create Case API successfull", true);
		}else
		{ReportUtil.reportWebElement("Create Case API Failed", false);}
		}

	}



