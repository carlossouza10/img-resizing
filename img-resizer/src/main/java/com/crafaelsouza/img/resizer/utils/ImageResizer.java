package com.crafaelsouza.img.resizer.utils;

import java.awt.image.BufferedImage;

public interface ImageResizer {

	BufferedImage resizeImageSmall(BufferedImage image);

	BufferedImage resizeImageMedium(BufferedImage image);
	
	BufferedImage resizeImageLarge(BufferedImage image);
}
