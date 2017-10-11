package com.markit.framework.api.pageMethods;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.markit.common.framework.util.Config;
import com.markit.common.framework.util.DBConnection;
import com.markit.common.framework.util.DBUtility;
import com.markit.common.framework.util.ExcelUtil;

public class SDLAPI {

	public  Map<String,String> apiConfig=Config.loadConfig("src/testdata/API.properties");	
	Connection conn=DBConnection.dbConnections.get("KYC");
	
	public  void writeValueForAPIKey(String testDataPath,String sheetName,String KeyToUpdate,String setValue){
		Workbook wb=	ExcelUtil.getWorkBookObject(testDataPath);
		Sheet sheetObj=wb.getSheet(sheetName);
		Row fstRowObj=sheetObj.getRow(0);
		int colNo=ExcelUtil.getColumnNumberByColumnName("API_Key", fstRowObj);
		int rowNo=ExcelUtil.getRowNumberByColName(sheetObj, colNo, KeyToUpdate);	
		ExcelUtil.setColumnDataByColName(testDataPath, sheetName, "API_Value", rowNo, setValue);
	}
	
	public Map<String, Object> readAPIResponse(StringBuffer result) throws JSONException{
		JSONObject root = new JSONObject(result.toString().trim());
		 Map<String, Object> retMap = new HashMap<String, Object>();
		  if(root != null) {
		        retMap = toMap(root);
		    }
		    Map<String,Object> relationMap = (Map<String,Object>) ((List<Object>)retMap.get("relationshipList")).get(0);
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
		   
		  String value=relationMap.get(keyList[keyList.length-1]).toString();
		    if(keyList[keyList.length-1].contains("country")){
		    	String qry="select name from kyc.ref_country where code='"+value+"' ";
		    	value=DBUtility.getColData(conn, qry, "NAME");
		    }		    
		  try{
		  return value;
		  }catch(NullPointerException e){
		  return "Value not found in API Response";
		  }
	}

	public List<String> readAPIKeysFromExcel(String wbPath,String sheetName){	
	Workbook wbObj=ExcelUtil.getWorkBookObject(wbPath);
	Sheet wbSheet=wbObj.getSheet(sheetName);
	List<String> apiKeyList = new ArrayList<String>();
	for (int i=1;i<=wbSheet.getLastRowNum();i++){
		String value=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "Value", i);
	    if(!value.equals("null")){
	    	String api_key=ExcelUtil.getColumnDataByColName(wbPath, sheetName, "API_Key", i);
	    	try{
	    	apiKeyList.add(api_key);
	    	}catch(NullPointerException e){e.printStackTrace();}
	    }
	}
	return apiKeyList;
	}

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

}
