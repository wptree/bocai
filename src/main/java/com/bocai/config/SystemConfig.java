package com.bocai.config;
import java.io.IOException;
import java.util.Properties;

import com.bocai.common.constant.ActivityType;

public class SystemConfig {
	private static Properties bocaiProperties = new Properties();
	
	
//	public static final String DOMAIN_URL = "www.bocai007.com";  
//	public static final String SINA_APP_KEY = "100748713";
//	public static final String SINA_APP_KEY_SECRET = "1352bae7b8621f54b6cc8657ffce389e";
//	public static final String BOCAI_SINA_ID = "2082026727";
//	
//	public static final String RENREN_APP_KEY = "a93406aece99481db5231bc9c990e70a";
//	public static final String RENREN_APP_KEY_SECRET = "c645d3de92464825a2569b9274c7d23d";
//	public static final String BOCAI_RENREN_ID = "144507";
//	
//	// 应用认证授权的URL
	public static final String RENREN_OAUTH_AUTHORIZE_URL = "https://graph.renren.com/oauth/authorize";
	// 应用获取Access_token的URL
	public static final String RENREN_OAUTH_ACCESS_TOKEN_URL = "https://graph.renren.com/oauth/token";
	// 应用获取人人网session key的URL
	public static final String RENREN_API_SESSIONKEY_URL = "http://graph.renren.com/renren_api/session_key";
	//Renren API Call URL
	public static final String RENREN_API_CALL_URL = "http://api.renren.com/restserver.do";
	
	static {
		try {
			bocaiProperties.load(SystemConfig.class.getResourceAsStream("/bocai.properties"));
			for (Object key : bocaiProperties.keySet()) {
	            String value = bocaiProperties.getProperty(key.toString());
	            String goodValue = new String(value.getBytes("iso8859-1"), "utf-8");
	            bocaiProperties.setProperty(key.toString(), goodValue);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getSinaWeiboHelloImg(){
		return bocaiProperties.getProperty("SINA_WEIBO_IMG_SAY_HELLO","http://static.bocai007.com/bocai/images/l_head1_5.png");
	}
	
	public static String uploadTempDir(){
		return bocaiProperties.getProperty("UPLOAD_TEMPORARY_DIR","/tmp");
	}
	
	public static String imageContext(){
		return bocaiProperties.getProperty("BOCAI_IMAGE_CONTEXT","http://upload.bocai007.com/bocai");

	}
	
	public static String staticContext(){
		return bocaiProperties.getProperty("BOCAI_STATIC_CONTEXT","http://static.bocai007.com/bocai");
	}
	
	public static String jtemplatePath(){
		return bocaiProperties.getProperty("JTEMPLATE_PATH", "WEB-INF/page/jtemplate");
	}
	
	public static String getRenrenBackUrl(){
		return bocaiProperties.getProperty("RENREN_CALLBACK_URL","http://www.bocai007.com/bocai/login!renrenUserLogin.bc");
	}
	
	public static String getDomainUrl(){
		return bocaiProperties.getProperty("DOMAIN_URL","www.bocai007.com");
	}
	
	public static String getImgMark(){
		return bocaiProperties.getProperty("BOCAI_IMG_MARK","@波菜网 BOCAI007.COM");
	}
	
	public static String getSinaAppKey(){
		return bocaiProperties.getProperty("SINA_APP_KEY","100748713");
	}
	
	public static String getSinaAppSecretKey(){
		return bocaiProperties.getProperty("SINA_APP_KEY_SECRET","1352bae7b8621f54b6cc8657ffce389e");
	}
	
	public static String getBocaiSinaId(){
		return bocaiProperties.getProperty("BOCAI_SINA_ID","2082026727");
	}
	
	public static String getRenrenAppKey(){
		return bocaiProperties.getProperty("RENREN_APP_KEY","a93406aece99481db5231bc9c990e70a");
	}
	
	public static String getRenrenAppSecretKey(){
		return bocaiProperties.getProperty("RENREN_APP_KEY_SECRET","c645d3de92464825a2569b9274c7d23d");
	}
	
	public static String getBocaiRenrenId(){
		return bocaiProperties.getProperty("BOCAI_RENREN_ID","144507");
	}
	
	public static String getContext(){
		return bocaiProperties.getProperty("CONTEXT", "bocai");
	}
	
	public static String getStaticRoot(){
	return bocaiProperties.getProperty("BOCAI_STATIC_ROOT", "/deploy/static");
}
	
	public static String getUserDefaultAvatar(){
		return imageContext() + bocaiProperties.getProperty("BOCAI_USER_AVATAR_DEFAULT", "/images/avatar.jpg");
	}
	
	public static String getMissingDishImg(){
		return bocaiProperties.getProperty(MISSING_DISH_IMG, "/bocai/imags/missing.jpg");
	}
	
	public static int[] getBokeLevelScoreArray(){
		int[] intArray = null;
		try {
			String str = bocaiProperties.getProperty("BOKE_LEVEL_SCORE_LEVEL_DEFINE","100,500,1000,5000");
			String[] strArray = str.split(",");
			intArray = new int[strArray.length];
			for(int i = 0; i<strArray.length; i++){
				intArray[i] = Integer.parseInt(strArray[i]);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return intArray;
	}
	
	public static int[] getSpotImgSizeDefineArray(){
		int[] intArray = null;
		try {
			String str = bocaiProperties.getProperty("SPOT_IMG_SIZE_DEFINE_ARRAY","600,480,180,120,90,32");
			String[] strArray = str.split(",");
			intArray = new int[strArray.length];
			for(int i = 0; i<strArray.length; i++){
				intArray[i] = Integer.parseInt(strArray[i]);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return intArray;
	}
	
	public static String getBokeLevelName(int level){
		return bocaiProperties.getProperty("BOKE_LEVEL_NAME_"+level,"初级波客");
	}
	
	public static Integer getBokeBaseScore(){
		return Integer.valueOf(bocaiProperties.getProperty("USER_BASE_SCORE","100"));
	}
	
	public static String getSpotImgUri(){
		return bocaiProperties.getProperty("BOCAI_SPOT_IMG_URI","http://upload.bocai007.com/bocai/spot/");
	}
	
	public static String getImgSize2Weibo(){
		return bocaiProperties.getProperty("SPOT_IMG_TO_WEIBO_SIZE","480");
	}
	
	public static Integer getActivityScore(ActivityType type){
		switch(type){
			case SPOT_UPLOAD:
				return Integer.valueOf(bocaiProperties.getProperty("USER_UPLOAD_SCORE", "50"));
			case USER_FOLLOW_USER:
				return Integer.valueOf(bocaiProperties.getProperty("USER_FOLLOW_SCORE", "25"));
			case USER_FOLLOW_PLACE:
				return Integer.valueOf(bocaiProperties.getProperty("USER_FOLLOW_SCORE", "25"));
			case USER_FOLLOW_DISH:
				return Integer.valueOf(bocaiProperties.getProperty("USER_FOLLOW_DISH", "25"));
			case SPOT_WANTED:
				return Integer.valueOf(bocaiProperties.getProperty("USER_WANT_SCORE", "15"));
			case SPOT_NOMMED:
				return Integer.valueOf(bocaiProperties.getProperty("USER_NOM_SCORE", "15"));
			case COMMENT_SUMMIT:
				return Integer.valueOf(bocaiProperties.getProperty("USER_COMMENT_SUMMIT", "15"));
			case COMMENT_GREAT:
				return Integer.valueOf(bocaiProperties.getProperty("USER_COMMENT_GREAT_SCORE", "10"));
			case COMMENT_GOOD:
				return Integer.valueOf(bocaiProperties.getProperty("USER_COMMENT_GOOD_SCORE", "10"));
			default:
				return 0;
		}
	}
	
	public static String getSpotImgPath(){
		return bocaiProperties.getProperty("BOCAI_SPOT_IMG_PATH");
	}
	
	public static String getUploadOrgPath(){
		return bocaiProperties.getProperty("BOCAI_UPLOAD_ORG_PATH");
	}
	
	public static String getUploadOrgURI(){
		return bocaiProperties.getProperty("BOCAI_UPLOAD_ORG_URI");
	}
	
	public static String getDownloadPath(){
		return bocaiProperties.getProperty("BOCAI_DOWNLOAD_PATH");
	}
	
	public static final String TENCENT_APP_KEY = "";
	public static final String TENCENT_APP_KEY_SECRET = "";
	public static final String DOUBAN_APP_KEY = "";
	public static final String DOUBAN_APP_KEY_SECRET = "";
	public static final String DISH_IMAGE_STORAGE_PATH = "/DishImg";
	public static String DISH_IMG_COMMON_FOLDER = "180";
	public static final String MISSING_DISH_IMG = "MISSING_DISH_IMG";


}
