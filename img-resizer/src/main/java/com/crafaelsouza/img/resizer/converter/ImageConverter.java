package com.crafaelsouza.img.resizer.converter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageConverter {

	public static byte[] convertBuffImageToByteArray(BufferedImage bufferedImage, String extension) throws IOException {
	        byte[] arrayImage = null;
	        try{
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
	            ImageIO.write(bufferedImage, extension, out);
	            out.flush();
	            arrayImage = out.toByteArray();
	            out.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }

	        return arrayImage;
	}
	
	 public static BufferedImage convertURLImageToBuffImage(String uri) {
		BufferedImage image = null;

		try {
			URL url = new URL(uri);
			image = ImageIO.read(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return image;
	    }
}
