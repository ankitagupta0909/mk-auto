package com.markit.demo;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyJsonTest {


		  public static void listJson(JSONObject json) throws JSONException {
		    listJSONObject("", json);
		  }

		  private static void listObject(String parent, Object data) throws JSONException {
		    if (data instanceof JSONObject) {
		      listJSONObject(parent, (JSONObject)data);
		    } else if (data instanceof JSONArray) {
		      listJSONArray(parent, (JSONArray) data);
		    }  
		  }

		  private static void listJSONObject(String parent, JSONObject json) throws JSONException {
		    Iterator it = json.keys();
		    while (it.hasNext()) {
		      String key = (String)it.next();
		      Object child = json.get(key);
		      String childKey = parent.isEmpty() ? key : parent + "." + key;
		      listObject(childKey, child);
		    }
		  }

		  private static void listJSONArray(String parent, JSONArray json) throws JSONException {
		    for (int i = 0; i < json.length(); i++) {
		      Object data = json.get(i);
		      listObject(parent + "[" + i + "]", data);
		    }
		  }
}
