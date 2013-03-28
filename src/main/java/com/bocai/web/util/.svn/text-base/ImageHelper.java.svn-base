package com.bocai.web.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;

import org.aspectj.util.FileUtil;

import com.bocai.common.image.ImageInfo;
import com.bocai.common.image.ImageScale;
import com.bocai.common.image.ImageScaleImpl;
import com.bocai.config.SystemConfig;

/**
 * Image resize tool
 * @author wpeng
 *
 */
public class ImageHelper {
	
	public static void main(String[] args){
		File orginalFile = new File("E:\\Workspace\\Temp\\Test\\羔羊肉.jpg");
		int[] sizeArray = {600,480,180,120,90,32};
		resizeSpotImg(orginalFile,"E:\\Workspace\\Temp\\Test",sizeArray, "BOCAI007.COM",90);
	}
	
	public static ImageInfo resizeSpotImg(File originalFile,String rootDir, int[] sizeArray,String mark,double degree){
		File dir = new File(rootDir);
		dir.mkdirs();
		ImageScale imageScale = new ImageScaleImpl();
		System.out.println("ImageScale class:::: "+imageScale.getClass().getName());
		ImageInfo info = new ImageInfo();
		File tempFile = null;
		File squareFile = null;
		try {
			info.setInput(new FileInputStream(originalFile));
			info.check();
			
			StringBuffer filePath = new StringBuffer(rootDir);
			filePath.append(File.separator).append("square").append(".").append(info.getFormatName());
			
			int squareSize, cutTop, cutLeft;
			int distance = info.getWidth()-info.getHeight();
			
			if(distance != 0){
				squareFile = new File(filePath.toString());
				if(distance>0){
					cutTop = 0;
					cutLeft = distance/2;
					squareSize = info.getHeight();
				}else{
					distance = Math.abs(distance);
					cutTop = distance/2;
					cutLeft = 0;
					squareSize = info.getWidth();
				}
				imageScale.resizeFix(originalFile, squareFile, squareSize, squareSize, cutTop, cutLeft, squareSize, squareSize,degree);
			}else{
				squareSize = info.getWidth();
				squareFile = originalFile;
			}			
			
			for(int size : sizeArray){
				filePath = new StringBuffer(rootDir);
				filePath.append(File.separator).append(size).append(".").append(info.getFormatName());
				tempFile = new File(filePath.toString());
				filePath = null;
				imageScale.resizeFix(squareFile, tempFile, size, size,degree);
				if(mark == null || "".equals(mark)){
					mark = SystemConfig.getImgMark();
				}
				imageScale.imageMark(tempFile, tempFile, 400, 400, 4, -120, -20, mark, Color.white, 14, 50);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(squareFile!=null){
				squareFile.delete();
			}
		}
		return info;
	}
	
	public static String getSpotImagePath(Long id, int size, String imgType){
		StringBuilder path = new StringBuilder();
		path.append("spot/")
			.append(id)
			.append("/")
			.append(size).append(".").append(imgType);
		return path.toString();
	}
	
	public static String getMissingImagePath(int size){
		String missingImg = SystemConfig.getMissingDishImg();
		return missingImg.replace(".jpg", "." + size+".jpg");
	}
	
	
}
