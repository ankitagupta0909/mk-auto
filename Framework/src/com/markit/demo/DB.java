package com.markit.demo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.sql.Connection;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.ClientProtocolException;
import org.jfree.util.Log;

import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ReportUtil;
import com.markit.framework.api.pageMethods.KYCAPI;


public class DB {
	public  Map<String,String> apiConfig=Config.loadConfig("src/testdata/API.properties");	
	Connection conn=DBConnection.dbConnections.get("KYC");
	
	public void entityMergeTC(String kycEntname, String mcpmEntName) throws ClientProtocolException, IOException, ServiceException, URISyntaxException, org.apache.http.auth.AuthenticationException {		
		//kycEntname="legalName";
		String kycEntId=null;
		String mcpmEntId=null;
		try{

		String qry="select A.ENTITY_RELATION_ID AS relationshipId,"+
		           "BMY.LEI AS lei,BMY.ENTITY_NAME AS trueLegalName,"+
				    "CL.MCPM_CLIENT_ID AS institutionId,CL.NAME AS institutionName,"+
		            "BCPTY.lei as cptyLei,BCPTY.ENTITY_NAME as cptyLegalName"+
				    "CLCPTY.MCPM_CLIENT_ID AS cptyInstitutionId,CLCPTY.NAME AS cptyInstitutionName"+
				    "from"+
				    "RS.ENTITY_RELATION A left outer join s_client_entity_screener BMY"+
				    "on (A.FROM_ENTITY_ID=BMY.ENTITY_ID and A.FROM_CLIENT_ID=BMY.CLIENT_ID)"+
				    "left outer join s_client_entity_screener BCPTY"+
				    "on (A.TO_ENTITY_ID=BCPTY.ENTITY_ID and A.TO_CLIENT_ID=BCPTY.CLIENT_ID)"+
				    "left outer join kyc.client CL on (BMY.CLIENT_ID=CL.CLIENT_ID)"+
				     "left outer join kyc.client CLCPTY on (BCPTY.CLIENT_ID=CLCPTY.CLIENT_ID)"+
				    "where";
				
		kycEntId=DBUtility.getColData(conn, qry, "ENTITY_ID");
	
	}
	catch(Exception e){
		e.printStackTrace();}
	}
	
}
