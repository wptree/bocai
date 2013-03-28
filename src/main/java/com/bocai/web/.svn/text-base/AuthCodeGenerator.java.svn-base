package com.bocai.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bocai.common.constant.AppConstant;

@SuppressWarnings("serial")
public class AuthCodeGenerator extends HttpServlet {

	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	//private static String charsLong = "23456789abcdefghjklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
	private static String charsCap = "023456789abcdefhkmnrstuvwxz";
	//private static String charsShort = "0123456789";

	private static String chars = charsCap;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			int charsLength = chars.length();

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			int width = 70, height = 26;
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			Graphics g = image.getGraphics();

			Random random = new Random();

			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			g.setFont(new Font("Times New Roman", Font.ITALIC, height));

			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 22; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}

			StringBuilder sRand = new StringBuilder();
			String[] fontNames = {"Verdana", "Tahoma", "Hei", "宋体", "Helvetica", "Arial", "sans-serif"};

			for (int i = 0; i < 4; i++) {
				g.setFont(new Font(fontNames[random.nextInt(fontNames.length)], Font.ITALIC,height*9/10));
				char rand = chars.charAt(random.nextInt(charsLength));
				sRand.append(rand);

				g.setColor(new Color(20 + random.nextInt(110), 20 + random
						.nextInt(110), 20 + random.nextInt(110)));
				g.drawString(String.valueOf(rand), 16 * i + 3, height - random.nextInt(4));
			}

			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 10; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(width);
				int yl = random.nextInt(width);
				g.drawLine(x, y, x + xl, y + yl);
			}
			
			request.getSession().setAttribute(AppConstant.SESSION_AUTH_CODE,sRand.toString());

			g.dispose();

			try {
				Thread.sleep(100);
			} catch (Exception ex) {
			}
			OutputStream os = response.getOutputStream();
			ImageIO.write(image, "JPEG", os);
			os.flush();
			os.close();
		} catch (Exception ex) {

		}
	}

}
