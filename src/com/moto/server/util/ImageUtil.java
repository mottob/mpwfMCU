package com.moto.server.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

public void scale(String srcImageFile, double standardWidth,
    double standardHeight) {
   try {
    BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件

    Image image = src.getScaledInstance((int) standardWidth,
      (int) standardHeight, Image.SCALE_DEFAULT);
    BufferedImage tag = new BufferedImage((int) standardWidth,
      (int) standardHeight, BufferedImage.TYPE_INT_RGB);
    Graphics g = tag.getGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    ImageIO.write(tag, "JPG", new File(srcImageFile + "_scale.JPG"));// 输出到文件流
   } catch (IOException e) {
    e.printStackTrace();
   }
}

public static void main(String[] args) {
   cut("E:\\Workspaces\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\LBSServer\\upload_pic\\1317627277137", 236,236,400,252);
}

public static File cut(String srcImageFile, int width,
	    int height, int startwidth, int startheight) {
	   try {
		
	    Image img;
	    ImageFilter cropFilter;
	    File imgFile = new File(srcImageFile);
	    	
    	BufferedImage bi = ImageIO.read(imgFile);
    	int srcWidth = bi.getWidth(); // 源图宽度
    	int srcHeight = bi.getHeight(); // 源图高度
    	
    	if (srcWidth > width && srcHeight > height) {
    		Image image = bi.getScaledInstance(srcWidth, srcHeight,
    				Image.SCALE_DEFAULT);
    		
    		cropFilter = new CropImageFilter(startwidth, startheight, width, height);
    		img = Toolkit.getDefaultToolkit().createImage(
    				new FilteredImageSource(image.getSource(), cropFilter));
    		BufferedImage tag = new BufferedImage(width, height,
    				BufferedImage.TYPE_INT_RGB);
    		Graphics g = tag.getGraphics();
    		g.drawImage(img, 0, 0, null);
    		g.dispose();
    		File cutedImg = new File(srcImageFile+".bak");
    		ImageIO.write(tag, "JPG", cutedImg);
    		imgFile.delete();
    		return cutedImg;
    	}
	    
	   } catch (Exception e) {
	    e.printStackTrace();
	    return null;
	   }
	   return null;
	}
}