package com.crafaelsouza.img.resizer.repository;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface ImageRepository {

	public byte[] findImage(String imgName);
	
	public List<String> findAllImagesName();
	
	public void save(BufferedImage bufferedImage, String imgName) throws IOException;
}
