package com.markit.framework.api.pageMethods;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.xml.rpc.ServiceException;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.marketxs.userxs.client.service.UserXsClient;
import com.marketxs.userxs.session.api.SessionService;
import com.marketxs.userxs.session.api.SimpleSessionRequest;
import com.marketxs.userxs.session.api.SimpleSessionRequestByEmail;
import com.marketxs.userxs.session.objects.interfaces.AccountTicket;
import com.markit.common.framework.util.Config;

public class Authentication {

	public static Map<String,String> apiConfig;
	
	
	public AccountTicket returnTicket() throws MalformedURLException, ServiceException, RemoteException{
		apiConfig=Config.loadConfig("src/testdata/API.properties");
		
		UserXsClient clientSession = new UserXsClient("https://soap.userxs-qa.markit.com");
		SessionService sessionService = clientSession.getSessionService();
		SimpleSessionRequest simpleSessionRequest = new SimpleSessionRequestByEmail(
				apiConfig.get("password"), "Subscriber-Portal", "", "", "", "", "", -1, "", 
				true, true, false, "", null,
				"", apiConfig.get("userName"));
		AccountTicket ticket = sessionService.openSession2(simpleSessionRequest);
		System.out.println(ticket.getTicketId());
		System.out.println(ticket.getOwnerName());
		return ticket;
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
}
