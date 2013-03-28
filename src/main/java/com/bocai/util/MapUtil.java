package com.bocai.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.csvreader.CsvReader;


public class MapUtil {
	
	 public static void main(String[] args){
		 System.out.println(getLatlng("杭州","西湖区)"));
	 }
	
	public static String getLatlng(String prefix, String address){
		   String ret = "";
		   String[] arr = new String[4];
		   CsvReader reader = null;
		   if(address != null && !address.equals("")){
		   try {
			     address = URLEncoder.encode(prefix + address,"UTF-8");//进行这一步是为了避免乱码
			     arr[0] = address;
			     arr[1] = "csv";
			     arr[2] = "false";
			     arr[3] = "ABQIAAAAn3VSODyqqXeIJ3HdjlH4FBSStG6jCoeTkFeNoMIrteim0fVcGhTfJXCEbhFRS76T4ZFqQ6cdnncXKA";
			     String url = MessageFormat.format("http://ditu.google.cn/maps/geo?q={0}&output={1}&sensor={2}&key={3}",arr);
			     URL urlmy = null;
			    
			     urlmy = new URL(url);
			     HttpURLConnection con = (HttpURLConnection) urlmy.openConnection();
			     con.setFollowRedirects (true );
			     con.setInstanceFollowRedirects(false );
			     con.connect();
			     
			     BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			     String s = "";
			     StringBuffer sb = new StringBuffer("");
			     while ((s = br.readLine()) != null ) {
			      sb.append(s);
			     }
			     ret = sb.toString();
			     
		    } catch (UnsupportedEncodingException e1) {
		    	System.out.println("转码失败");
		    	e1.printStackTrace();
		    } catch (MalformedURLException e) {
		    	System.out.println("通过http方式获取地址信息失败");
		    	e.printStackTrace();
		    } catch (IOException e) {
		    	System.out.println("文件读取失败");
		    	e.printStackTrace();
		    }finally{
		    	if(reader!=null){
		    		reader.close();
		    	}
		    }
		   }
		   return ret;
		} 

}
