package com.bocai.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class FoodPic {
	
	  public static void main(String[] args) throws Exception {
		  optimize("C:\\Users\\wpeng\\Desktop\\web\\东北一裹香-干锅包心菜.jpg","","");
		}
	
	private static void download(String dirRoot,String fileName,String tempCookie){
		try {
			Document doc = Jsoup.connect("http://foodpic.net/cooker.php")
			.cookie("PHPSESSID", tempCookie)
			.userAgent("Mozilla")
			.timeout(5000)
			.get();
			
			Elements images = doc.select("img[id*=afterPic]");
	    	for(Element img : images){
	    		System.out.println(img.attr("abs:src"));
	    		//HttpUtil.urlToFile(dirRoot, fileName,"http://foodpic.net/"+img.attr("abs:src"));
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


  
  private static void optimize(String fileFullPath, String dishName, String newDir){
	  try {
		  	HttpClient httpclient = new DefaultHttpClient();
//		    HttpHost proxy = new HttpHost("proxy.statestr.com", 80);
//		    httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		    httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2965);

		    HttpPost httppost = new HttpPost("http://foodpic.net/post.php");
		    File file = new File(fileFullPath);
		    httppost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);

		    MultipartEntity mpEntity = new MultipartEntity();
		    ContentBody cbFile = new FileBody(file, "image/jpeg");
		    mpEntity.addPart("data", cbFile);
		    httppost.setEntity(mpEntity);
		       
			// Create local HTTP context
			HttpContext localContext = new BasicHttpContext();
			// Bind custom cookie store to the local context
			localContext.setAttribute(ClientContext.COOKIE_STORE, prepareCookie());    
		    HttpResponse response = httpclient.execute(httppost,localContext);
		    Header h1 = response.getFirstHeader("Set-Cookie");
		    HeaderElement[] hhh = h1.getElements();
		    String tempCookie = "";
		    for(HeaderElement e : hhh){
		    	if(e.getName().equals("PHPSESSID")){
		    		tempCookie = e.getValue();
		    	}
		    }
		    httpclient.getConnectionManager().shutdown();
		    
		    download(newDir,dishName,tempCookie);
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	private static CookieStore prepareCookie(){
		 CookieStore cookieStore = new BasicCookieStore();
		 BasicClientCookie sessionId = new BasicClientCookie("PHPSESSID", "51p1u5vmlam8e8lug71gurb853");
		 BasicClientCookie utma = new BasicClientCookie("__utma", "168766902.1839165129.1326263974.1326775997.1326780997.8");
		 BasicClientCookie utmz = new BasicClientCookie("__utmz", "168766902.1326272277.2.2.utmcsr=noritsu.info|utmccn=(referral)|utmcmd=referral|utmcct=/plus/tech/accusmart/nas_development.php");
		 BasicClientCookie utmc = new BasicClientCookie("__utmc", "168766902");
		 BasicClientCookie utmb = new BasicClientCookie("__utmb", "168766902.1.10.1326780997");
		 cookieStore.addCookie(sessionId); 
		 cookieStore.addCookie(utma); 
		 cookieStore.addCookie(utmz); 
		 cookieStore.addCookie(utmc); 
		 cookieStore.addCookie(utmb); 
		 
		 return cookieStore;
	}
}
