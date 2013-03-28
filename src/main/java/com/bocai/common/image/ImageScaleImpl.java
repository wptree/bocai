package com.bocai.common.image;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;

import magick.Magick;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片缩小类
 * 
 * 根据环境情况选择java图片缩小方式或专业的magick图片缩小方式
 * 
 * @author liufang
 * 
 */
public class ImageScaleImpl implements ImageScale {
	private static final Logger log = LoggerFactory
			.getLogger(ImageScaleImpl.class);

	public void resizeFix(File srcFile, File destFile, int boxWidth,
			int boxHeight,double degree) throws Exception {
		if (isMagick) {
			MagickImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight,degree);
		} else {
			CommonImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight);
		}
	}

	public void resizeFix(File srcFile, File destFile, int boxWidth,
			int boxHeight, int cutTop, int cutLeft, int cutWidth, int catHeight,double degree)
			throws Exception {
		if (isMagick) {
			System.out.println("Using JMagick: "+ isMagick);
			MagickImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight,
					cutTop, cutLeft, cutWidth, catHeight,degree);
		} else {
			System.out.println("Using JMagick: "+ isMagick);
			CommonImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight,
					cutTop, cutLeft, cutWidth, catHeight);
		}
	}

	public void imageMark(File srcFile, File destFile, int minWidth,
			int minHeight, int pos, int offsetX, int offsetY, String text,
			Color color, int size, int alpha) throws Exception {
		if (isMagick) {
			MagickImageScale.imageMark(srcFile, destFile, minWidth, minHeight,
					pos, offsetX, offsetY, text, color, size, alpha);
		} else {
			CommonImageScale.imageMark(srcFile, destFile, minWidth, minHeight,
					pos, offsetX, offsetY, text, color, size, alpha);
		}
	}

	public void imageMark(File srcFile, File destFile, int minWidth,
			int minHeight, int pos, int offsetX, int offsetY, File markFile)
			throws Exception {
		if (isMagick) {
			MagickImageScale.imageMark(srcFile, destFile, minWidth, minHeight,
					pos, offsetX, offsetY, markFile);
		} else {
			CommonImageScale.imageMark(srcFile, destFile, minWidth, minHeight,
					pos, offsetX, offsetY, markFile);
		}
	}

	/**
	 * 检查是否安装magick
	 */
	public ImageScaleImpl() {
		if (tryMagick) {
			try {
				System.setProperty("jmagick.systemclassloader", "no");
				new Magick();
				System.out.println("using jmagick");
				isMagick = true;
			} catch (Throwable e) {
				e.printStackTrace();
				log.warn("load jmagick fail, use java image scale. message:{}",e.getMessage());
				isMagick = false;
			}
		} else {
			System.out.println("jmagick is disabled.");
			isMagick = false;
		}
	}

	private boolean isMagick = false;
	private boolean tryMagick = true;

	public void setTryMagick(boolean tryMagick) {
		this.tryMagick = tryMagick;
	}
	
	public static void main(String[] args){
		ImageScale scale = new ImageScaleImpl();
		
		File file = new File("C:\\Users\\wpeng\\Desktop\\guzz\\0101323f6803dd0bcc-max.jpg");
		File file2 = new File("C:\\Users\\wpeng\\Desktop\\guzz\\0101323f6803dd0bcc-max1.jpg");
		ImageInfo info = new ImageInfo();
		
		try {
			info.setInput(new FileInputStream(file));
			info.check();
			scale.imageMark(file, file2, 400, 300, 4, -150, -20, "baocai007.com", Color.blue, 14, 50);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
