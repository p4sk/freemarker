package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.xml.crypto.dsig.XMLObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import main.java.VO.HelloFreemarker;

public class Utils {

	static final Logger logger = LogManager.getLogger(HelloFreemarker.class.getName());

	/**
	 * Questo metodo prende in input uno username e ritorna il  tipo di ambiente settato nel config.properties
	 * @param username
	 * @return
	 */
	public String getProperties( String username ){

		Properties props = new Properties();
		InputStream inputStream = null;
		String propFileName = "config.properties";

		String ambiente = System.getenv("ENV");
		String property = "";

		try {

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				logger.info("property file '" + propFileName + "' not found in the classpath");
			}

			if(ambiente == "local" || ambiente == null) {
				property = "local."+username;
			}else{
				property = ambiente + "." +username;
			}

		} catch (Exception e) {
			logger.error("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 

		return props.getProperty(property);

	}

	/**
	 * Questo metodo ritorna un object dalla myUrl passatagli in input 
	 * @param myUrl
	 * @param methode (GET o POST)
	 * @param content (null o json o xml)
	 * @return
	 * @throws IOException
	 */
	public Object doCall(String myUrl, String methode, String content) throws IOException{

		URL url = new URL(myUrl);

		HttpURLConnection connection = null;

		Object object = null ;
		XMLObject xmlObject = null;

		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			if(content == null || content == "json"){
				connection.setRequestProperty("Content-Type", "application/json");
			}
			else if(content == "xml"){
				connection.setRequestProperty("Content-Type", "application/xml"); 
			}

			// setto lo User-Agent per evitare un HTTP error code 403
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

			connection.setRequestProperty("charset", "utf-8");
			connection.connect();

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			if (connection.getRequestProperty("Content-Type") != "application/json") {
				throw new RuntimeException("Failed : Content-Type : " + connection.getRequestProperty("Content-Type"));
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String str;
			StringBuffer stringBuffer = new StringBuffer();

			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
				stringBuffer.append("\n");
			}

			str = stringBuffer.toString();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(str);
			JSONArray msg = (JSONArray) obj;
			Iterator<?> iterator = msg.iterator();

			if(content == null || content == "json"){
				object = getObjectContent(object, iterator);
			}
			else if(content == "xml"){
				xmlObject = getXMLContent(xmlObject, iterator);
			}
			else{
				return null;
			}

		} catch (Exception e) {
			logger.error("Errore: " +e.getMessage());
		}finally{
			connection.disconnect();
		}

		return object;

	}
	public JSONArray getObjectContent(Object o, Iterator<?> iter){
		JSONArray list = new JSONArray();
		while (iter.hasNext()) {
			o=iter.next();
			list.add(o);
		}
		return list;
	}
	

	public XMLObject getXMLContent(XMLObject xmlObj, Iterator<?> iter){
		while (iter.hasNext()) {
			xmlObj=(XMLObject)iter.next();
		}
		return xmlObj;
	}

}
