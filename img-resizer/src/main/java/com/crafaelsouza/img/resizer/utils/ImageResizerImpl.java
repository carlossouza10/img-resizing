package com.crafaelsouza.img.resizer.utils;

import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crafaelsouza.img.resizer.config.ImageConfig;

@Component
public class ImageResizerImpl implements ImageResizer {

	@Autowired
	private ImageConfig imageConfig;

	@Override
	public BufferedImage resizeImageSmall(BufferedImage image) {
		return resizeImage(image, imageConfig.getSmallWidth(), imageConfig.getSmallHeight());
	}

	@Override
	public BufferedImage resizeImageMedium(BufferedImage image) {
		return resizeImage(image, imageConfig.getMediumWidth(), imageConfig.getMediumHeight());
	}

	@Override
	public BufferedImage resizeImageLarge(BufferedImage image) {
		return resizeImage(image, imageConfig.getLargeWidth(), imageConfig.getLargeHeight());
	}
	
	private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
		BufferedImage resizedImage = null;
		resizedImage = Scalr.resize(originalImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, width, height);
		return resizedImage;
	}	
}
