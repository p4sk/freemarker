package main.java.VO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test {

	static final Logger logger = LogManager.getLogger(Meteo.class.getName());

	public static void main(String[] args) throws IOException {


		//			URL url = new URL("http://www.google.com");
		URL url = new URL("http://jsonplaceholder.typicode.com/users");

		HttpURLConnection connection = null;

		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");

			// devo settare lo User-Agent per evitare un HTTP error code 403
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

			connection.setRequestProperty("charset", "utf-8");
			connection.connect();

			logger.info(connection.getContentType());
			//				logger.info(connection.getHeaderField("Content-Type"));

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
			
//			if(content == ""){
				Object a ;
				while (iterator.hasNext()) {
					a=(Object)iterator.next();
	//				logger.info("A: " +a.get("name"));
					logger.info("A: " +a);
				}
//			}
			

		} catch (Exception e) {
			logger.error("Errore: " +e.getMessage());
		}finally{
			connection.disconnect();
		}

	}
	public JSONObject getJsonContent(Iterator<?> iterator){
		JSONObject a = null ;
		while (iterator.hasNext()) {
			a=(JSONObject)iterator.next();
//			logger.info("A: " +a.get("name"));
		}
		return a;
	}
	
	public JSONObject getXMLContent(Iterator<?> iterator){
		JSONObject a = null ;
		while (iterator.hasNext()) {
			a=(JSONObject)iterator.next();
		}
		return a;
	}

}
