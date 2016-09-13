/**
 * Project Name:iotgp-web-util
 * File Name:UrlRequest.java
 * Package Name:cn.inovance.iotgp.web.common.util
 * Date:2015-2-5上午11:03:02
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * ClassName:UrlRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-2-5 上午11:03:02 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class UrlRequest {

	private static final Logger logger = Logger.getLogger(UrlRequest.class);
	public static String ConvertToString(InputStream inputStream) throws UnsupportedEncodingException{
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuilder result = new StringBuilder();
		String line = null;
		try {
			while((line = bufferedReader.readLine()) != null){
				result.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
				inputStreamReader.close();
				inputStream.close();
				bufferedReader.close();
			}catch(IOException e){
				e.printStackTrace();
				logger.error("ConvertToString 失败:"+e.getMessage());
			}
		}
		return result.toString();
	}


	public static String ConvertToString(FileInputStream inputStream) throws UnsupportedEncodingException{
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuilder result = new StringBuilder();
		String line = null;
		try {
			while((line = bufferedReader.readLine()) != null){
				result.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
				inputStreamReader.close();
				inputStream.close();
				bufferedReader.close();
			}catch(IOException e){
				e.printStackTrace();
				logger.error("ConvertToString 失败:"+e.getMessage());
			}
		}
		return result.toString();
	}
	
	public static String  requestByGet(String url){
	
		return request(url,"GET");
		
	}
	public static String  requestByPost(String url){
	
		return request(url,"POST");
	}
	
	public static String request(String urlstr,String method){
		String responseStr = "";
		logger.info("send request before:请求URL["+ urlstr+"],方法["+method+"]");
		try{  
            URL url = new URL(urlstr);  
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();  
             
            urlConnection.setRequestMethod(method);  
            urlConnection.connect();  
            //Connection Response From Test Servlet  
            InputStream inputStream = urlConnection.getInputStream();  
              
            //Convert Stream to String  
            responseStr = ConvertToString(inputStream); 
         logger.info("send request after:请求URL["+ urlstr+"],方法["+method+"]成功");
		}catch(IOException e){  
			logger.error("request 失败:"+e.getMessage());
        }  
		return responseStr;
	}
	
}

