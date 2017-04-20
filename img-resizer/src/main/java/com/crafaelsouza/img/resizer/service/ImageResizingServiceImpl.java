package com.crafaelsouza.img.resizer.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.crafaelsouza.img.resizer.converter.ImageConverter;
import com.crafaelsouza.img.resizer.repository.ImageRepository;
import com.crafaelsouza.img.resizer.rest.client.ImageServiceClient;
import com.crafaelsouza.img.resizer.utils.ImageResizer;

@Service
public class ImageResizingServiceImpl implements ImageResizingService {

	@Autowired
	private ImageServiceClient imageServiceClient;
	
	@Autowired
	private ImageResizer imageResizer;
	
	@Autowired
	private ImageRepository imageRepository;

	@Resource
	private Environment env;
	
	private static final String URI_KEY = "application.webservice.photos.endpoint";
	private static final String SUFFIX_IMG_SMALL = "_small";
	private static final String SUFFIX_IMG_MEDIUM = "_medium";
	private static final String SUFFIX_IMG_LARGE = "_large";
	
	@Override
	public List<String> getAllImagesNames() {
		return imageRepository.findAllImagesName();
	}

	@Override
	public byte[] findImage(String imgName) {
		return imageRepository.findImage(imgName);
	}

	@Override
	public void generateResizedImages() throws JSONException, IOException {
		String uri = env.getProperty(URI_KEY);
		JSONObject json = new JSONObject(imageServiceClient.retrieveImagesAsJson(uri));
		JSONArray imgPaths = json.getJSONArray("images");

		for (int i = 0; i < imgPaths.length(); i++) {
			JSONObject path = (JSONObject) imgPaths.get(i);
			String urlImg = (String) path.get("url");
			String fileName = getFileName(urlImg);
			String fileExtension = getExtension(urlImg);
			BufferedImage img = ImageConverter.convertURLImageToBuffImage(urlImg);
			resizeImageAndSave(img, fileName, fileExtension);
		}
	}

	private String getFileName(String url) {
		return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
	}
	
	private String getExtension(String url) {
		return url.substring(url.lastIndexOf(".") + 1);
	}
	
	private void resizeImageAndSave(BufferedImage image, String name, String extension) throws IOException {
        BufferedImage smallImage = imageResizer.resizeImageSmall(image);
        BufferedImage mediumImage = imageResizer.resizeImageMedium(image);
        BufferedImage largeImage = imageResizer.resizeImageLarge(image);
        
        imageRepository.save(smallImage, name + SUFFIX_IMG_SMALL + "." + extension);
        imageRepository.save(mediumImage, name + SUFFIX_IMG_MEDIUM + "." + extension);
		imageRepository.save(largeImage, name + SUFFIX_IMG_LARGE + "." + extension);
    }
}
