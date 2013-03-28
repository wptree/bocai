package com.bocai.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import com.bocai.config.SystemConfig;
import com.bocai.vo.RenrenAccessToken;

/**
 * 获取一个Access_token 对象
 * 
 * @author libo
 * 
 */
public class XiaoneiOauthAccessTokenUtil {
	public static RenrenAccessToken getAccessToken(String auth_code,String redirect_url) {
		// 此处用的是第三方的包，也可以用java本身的类
		// 设置请求参数，包括：client_id、client_secret、redirect_uri、code、和grant_type
		String apikey = SystemConfig.getRenrenAppKey();
		String secret = SystemConfig.getRenrenAppSecretKey();
		PostMethod method = new PostMethod(SystemConfig.RENREN_OAUTH_ACCESS_TOKEN_URL);
		method.addParameter("client_id",apikey);
		method.addParameter("client_secret",secret);
		method.addParameter("redirect_uri", redirect_url);
		method.addParameter("grant_type", "authorization_code");
		method.addParameter("code", auth_code);

		HttpClient client = new HttpClient();
		RenrenAccessToken access_token = new RenrenAccessToken();
		try {
			client.executeMethod(method);
			InputStream result = method.getResponseBodyAsStream();
			JSONObject jsonObj;
			try {
				StringBuffer accessline = new StringBuffer();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						result, "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					accessline.append(line);
				}
				jsonObj = new JSONObject(accessline.toString());
				access_token.setAccess_token(jsonObj.getString("access_token"));
				access_token.setExpires_in(jsonObj.getInt("expires_in"));
				return access_token;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
