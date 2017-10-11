package com.markit.common.framework.runtestsuit;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

import com.markit.common.framework.util.WebUtil;
import com.markit.framework.api.pageMethods.TokenGeneration;
import com.markit.framework.api.testcases.SDLAPI_TC;


@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
	MethodListener.class,
	com.markit.common.framework.util.Listener_ExcelReportGenerator.class })

public class TimestampAPI_RunTestSuit {

	{
		System.setProperty("atu.reporter.config","src/testdata/Reporter.properties");
		
	}
	
	@Test	
	public void tc_verify100recTimestamp() throws ClientProtocolException, IOException, InterruptedException, JSONException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
	TokenGeneration tok=new TokenGeneration(); 
	String token = tok.generateToken("sdlGenerateTokenURL","sdlTimestampUser","sdlTimestampPwd");
	SDLAPI_TC tc=new SDLAPI_TC();
	tc.tc_getSDLTimestampAPI(token, "2017-04-02", "",0);
	}
	
	@Test
	public void tc_verifyEmptyRelListTimestampURL()throws ClientProtocolException, IOException, InterruptedException, JSONException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		TokenGeneration tok=new TokenGeneration(); 
		String token = tok.generateToken("sdlGenerateTokenURL","sdlTimestampUser","sdlTimestampPwd");
		SDLAPI_TC tc=new SDLAPI_TC();
		
		String timestamp=WebUtil.getTommorowDate();
		tc.tc_getSDLTimestampAPI_EmptyRelList(token, timestamp, "");
	}

}
