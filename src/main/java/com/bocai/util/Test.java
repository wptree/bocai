package com.bocai.util;

import weibo4j.Weibo;
import weibo4j.WeiboException;



public class Test {

	/**
	 * 根据用户ID获取用户资料（授权用户） 
	 * @param args
	 * @throws WeiboException 
	 */
	public static void main(String[] args) throws WeiboException {
		System.setProperty("weibo4j.oauth.consumerKey", "100748713");
    	System.setProperty("weibo4j.oauth.consumerSecret", "1352bae7b8621f54b6cc8657ffce389e");
	
		//System.setProperty("weibo4j.oauth.consumerKey", "1552112022");
    	//System.setProperty("weibo4j.oauth.consumerSecret", "bd9e3b147ece106f9cee159758a28ec1");

		//System.setProperty("weibo4j.oauth.consumerKey", "4212139527");
    	//System.setProperty("weibo4j.oauth.consumerSecret", "e20e613826de4d084b279a0236c167de");
//	
//		System.setProperty("weibo4j.oauth.consumerKey", "2739201605");
//    	System.setProperty("weibo4j.oauth.consumerSecret", "4d68edfaa597de7237a390112235fab6");
	
		//SinaService sinaService = new SinaService();
		//RequestToken token = sinaService.getRequestToken();
    	//System.out.println("++++ " + token.getAuthorizationURL());
		
    	//Weibo weibo = new Weibo();
    	//RequestToken requestToken = weibo.getOAuthRequestToken("http://localhost:8080/bocai/main.bc");
//		
//    	try {
//			Weibo weibo = getWeibo(false);
//			User user = weibo.verifyCredentials();
//			//User user = weibo.showUser("孤傲的枫");
//			System.out.println("---- " + user.toString());
//		} catch (WeiboException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private static Weibo getWeibo(boolean isOauth) {
//		Weibo weibo = new Weibo();
//		if(isOauth) {//oauth验证方式 args[0]:访问的token；args[1]:访问的密匙
//			weibo.setToken("1ba88fc2d7eb58d6b2919e26e6c282f4", "dd5e439048202f3042fd36d89b80e062");
//		}else {//用户登录方式
//    		weibo.setUserId("1651222721");//用户名/ID
//    		weibo.setPassword("123456");//密码
//    		//weibo.setSource("2739201605");
//		}
//		return weibo;
	}
}

