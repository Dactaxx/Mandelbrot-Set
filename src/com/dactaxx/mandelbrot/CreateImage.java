package com.dactaxx.mandelbrot;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreateImage {
	public void makeImage() {
		int width = 3840; //double default for mandelbrotzoomhuge
		int height = 2160;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int zoom = 900; //default is 900, desktop background is 90000
		int a = 2;
		double px = -.5; //default is -.5, desktop background is -1.108
		double py = 0; //default is 0, desktop background is .23
		
		//stackoverflow probably means iterations or resolution is too high
		//default iterations: 3000
		
		long start = System.currentTimeMillis();
		for(double row = 0; row < width; row++) {
			for(double col = 0; col < height; col++) {
				mandelbrot((row - (width / 2)) / zoom + px, (col - (height / 2)) / zoom + py, (row - width / 2) / zoom + px, (col - height / 2) / zoom + py, row, col, bi, 3000, a);
				
			}
			
		}
		
		try {
			ImageIO.write(bi, "png", new File("mandelbrot.png"));
		}	catch(IOException e) {
			e.printStackTrace();
			
		}
		long end = System.currentTimeMillis();
		long duration1 = end - start;
		double duration = (double)duration1 / 1000;
		
		System.out.println(duration);
	}
	
	public void mandelbrot(double xo, double yo, double x, double y, double row, double col, BufferedImage bi, int limit, int a) {
		double x2 = Math.pow(x, a) - Math.pow(y, a) + xo;
		double y2 = (a * x * y) + yo;

		if(abs(x2, y2) > a) {
			bi.setRGB((int)row, (int)col, new Color(0, (int)((limit % 21) * (255 / 20)), (int)((limit % 51) * (255 / 50))).getRGB());
		} else {
			if (abs(x2, y2) <= a && limit !=0) {
				mandelbrot(xo , yo, x2, y2, row, col, bi, limit - 1, a);
				
			}	else if(limit == 0) {
				bi.setRGB((int)row, (int)col, new Color(0, 0, 0).getRGB());
				
				
			}
			
		}
	
	}
	
	public double abs(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		
	}
	
}
