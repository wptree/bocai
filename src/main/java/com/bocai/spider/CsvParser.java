package com.bocai.spider;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Service;

import com.bocai.common.constant.AppConstant;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Place;
import com.bocai.manager.DishManager;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.util.MapUtil;
import com.bocai.util.StringUtil;
import com.bocai.vo.SpotUploadRequest;
import com.csvreader.CsvReader;

@Service
public class CsvParser {
	
	@Resource private UserManager userManager; 
	@Resource private SpotManager spotManager;
	@Resource private DishManager dishManager;
	@Resource private PlaceManager placeManager;
	
	
	
	public static void main(String[] args){
		//parse("杭州","C:\\Users\\wpeng\\Desktop\\外婆家.csv");
	}
	
	public void parse(String city, File csvFile){
		if(csvFile != null){
			CsvReader reader = null;
			File image = null;
			try {
				reader = new CsvReader(new FileInputStream(csvFile), ',', Charset.forName("UTF-8"));
				SpotUploadRequest request = null;
				while(reader.readRecord()){
					request = new SpotUploadRequest();
					
					Long placeId = persistPlace(city, reader.get(2), reader.get(3), reader.get(4), reader.get(5),reader.get(6),reader.get(7));
					request.setSpotPlaceId(placeId.toString());
					
					if("".equals(reader.get(0))){
						continue;
					}
					
					request.setSpotPrice(StringUtil.getRandom(SpiderConfig.RandomSpotPrice()));
					request.setDescription(StringUtil.getRandom(SpiderConfig.RandomSpotDesc()));
					String userId = StringUtil.getRandom(SpiderConfig.RandomUserIDList());
					request.setCurrentUser(userManager.getById(Long.parseLong(userId)));
					request.setDishImageRoot(SystemConfig.getStaticRoot()+"/bocai/spot");
					request.setImgSizeArray(SystemConfig.getSpotImgSizeDefineArray());
					request.setPostBy("web");
					request.setSend2Sina(false);
					image = urlToFile(reader.get(0), reader.get(1));
					request.setSpotImg(image);
					
					persistDish(reader.get(0));
					request.setSpotDishName(reader.get(0));
					
					spotManager.persistSpot(request);
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(reader!=null){
					reader.close();
				}
				if(image!=null){
					image.deleteOnExit();
				}
			}
			
		}
	}
	
	public Long persistDish(String dishName){
		if(StringUtil.isNULL(dishName)){
			return 0L;
		}else{
			Dish dish = dishManager.getUniqueByProperty("name", dishName);
			if(dish!=null){
				return dish.getId();
			}else{
				dish = new Dish();
				dish.setName(dishName);
				return dishManager.save(dish);
			}	
		}
	}
	
	public Long persistPlace(String city, String primaryName, String secondaryName, String phone, String address,String lat, String lng){
		List list = placeManager.checkPlaceExist(primaryName, secondaryName);
		Long id = 0L;
		if(list.size()>0){
			id = (Long) list.get(0);
		}else{
			Place place = new Place();
			place.setName(primaryName);
			place.setSecondaryName(secondaryName);
			StringBuilder sb =  new StringBuilder();
			sb.append(place.getName());
			if(!StringUtils.isBlank(place.getSecondaryName())){
				sb.append(AppConstant.LEFT_PTS)
					.append(place.getSecondaryName())
					.append(AppConstant.RIGHT_PTS);
			}
			place.setFullName(sb.toString());
			Location lc = new Location();
			lc.setCity(city);
			lc.setStreet(address);
			lc.setLat(Double.parseDouble(lat));
			lc.setLng(Double.parseDouble(lng));
			place.setLocation(lc);
			
			place.setPhone(phone);
			place.setVenueType("地方特色菜");
			
			id = placeManager.save(place);
		}
		return id;
		
	}
	
	public File urlToFile(String imgName,String url){
		File file = null;
		if(!StringUtil.isNULL(url)){
			 try {
			    	String formatName = url.substring(url.lastIndexOf('.')+1);
			    	StringBuffer buf = new StringBuffer(SpiderConfig.ImageDownloadDir());
			    	buf.append(File.separator).append(imgName==null?"noName":imgName).append(".").append(formatName);
				 	file = new File(buf.toString());
				 	
					URL u = new URL(url);
					BufferedImage image = ImageIO.read(u);
					ImageIO.write(image, formatName, file);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}
		return file;
	}
	



}
