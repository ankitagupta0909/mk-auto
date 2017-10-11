package com.markit.common.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This Config util loads properties file and return the
 *         {@code Map}
 */
public class Config {

	/**loads config properties file
	 *  @param filePath
	 * @return Map<String,String>
	 */
	public static Map<String, String> loadConfig(String filePath) {
		HashMap<String, String> propMap = new HashMap<String, String>();
		File f = new File(filePath);
		InputStream is = null;
		try {
			is = new FileInputStream(f);
			Properties p = new Properties();
			p.load(is);
			propMap = new HashMap<String, String>((Map) p);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propMap;

	}
}
