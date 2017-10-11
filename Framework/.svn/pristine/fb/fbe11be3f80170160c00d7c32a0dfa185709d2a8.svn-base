package com.markit.framework.api.testcases;

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


public class KYCAPI_TC {
	public  Map<String,String> apiConfig=Config.loadConfig("src/testdata/API.properties");	
	Connection conn=DBConnection.dbConnections.get("KYC");
	
	public String entityMergeTC(String kycEntname, String mcpmEntName) throws ClientProtocolException, IOException, ServiceException, URISyntaxException, org.apache.http.auth.AuthenticationException {		
		//kycEntname="legalName";
		String kycEntId=null;
		String mcpmEntId=null;
		try{

		String qry="Select * from v_entity_screener where entity_name like '%"+kycEntname+"%'";
		kycEntId=DBUtility.getColData(conn, qry, "ENTITY_ID");
		String query="Select * from v_entity_screener where entity_name like '%"+mcpmEntName+"%'";
		mcpmEntId=DBUtility.getColData(conn, query, "ENTITY_ID");
		}catch(Exception e){
		Log.error("Some issue while fetching entity id from database");
		e.printStackTrace();
		}		
		KYCAPI a=new KYCAPI();		
			try {
				a.authenticateandGetResponse_EntityMerge(apiConfig.get("entityMergeURL"),kycEntId,mcpmEntId);
			} catch (KeyManagementException | UnrecoverableKeyException
					| NoSuchAlgorithmException | KeyStoreException | NullPointerException e) {
				e.printStackTrace();
			}			
		return kycEntId;
	}
	
	public String caseCreateTC(String kycEntId){

		KYCAPI a=new KYCAPI();		
		try {
			a.authenticateandGetResponse_CaseCreate(apiConfig.get("createCaseURL"), kycEntId);
		} catch (KeyManagementException | UnrecoverableKeyException
				| AuthenticationException | NoSuchAlgorithmException
				| KeyStoreException | IOException | ServiceException e) {
			e.printStackTrace();
		}
		
		String qry="select * from v_case where entity_id="+kycEntId +" ";
		String caseId=DBUtility.getColData(conn, qry, "CASE_ID");
		ReportUtil.reportWebElement("Create Case id: "+caseId, true);
		return caseId;
	}
}
