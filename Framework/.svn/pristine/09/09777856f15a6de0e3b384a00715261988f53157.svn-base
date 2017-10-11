package com.markit.common.framework.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.markit.common.framework.util.DBUtility;

/** creates DB connection and store them in a map 
 * for different products 
 * Returns single instance of connection
 * 
 * @author ankita.gupta
 *
 */
public class DBConnection {
	
	public static Map<String,Connection> dbConnections=new HashMap<>();
	public static Connection getDBInstance(Map<String,String> dbConfig,String productName)
	{
		if(dbConnections.get(productName)!=null){
			return dbConnections.get(productName);
		}else{
			
		    dbConnections.put(productName,DBUtility.createdbConnection(productName,dbConfig));
		return dbConnections.get(productName);
		}
	}
	

	
}



