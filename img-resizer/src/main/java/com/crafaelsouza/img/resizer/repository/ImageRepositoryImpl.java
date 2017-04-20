package com.crafaelsouza.img.resizer.repository;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.crafaelsouza.img.resizer.converter.ImageConverter;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

	@Autowired
	private DB mongoDB;
	
	private static final String GROUP_DATABASE = "application.images.group.name";
	private static final String EXTENSION = "application.images.extension";
	
	@Autowired
	private Environment env;
	
	public byte[] findImage(String imgName) {
		String groupName = env.getProperty(GROUP_DATABASE);
		GridFS gfsImage = new GridFS(mongoDB, groupName);
		GridFSDBFile gridFsImage = gfsImage.findOne(imgName);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
        	gridFsImage.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
	}
	
	public List<String> findAllImagesName() {
		String groupName = env.getProperty(GROUP_DATABASE);
		GridFS gfsPhoto = new GridFS(mongoDB, groupName);
        DBCursor dbCursor = gfsPhoto.getFileList();
        List<String> listAllImages = new ArrayList<>();
        while (dbCursor.hasNext()) {
            listAllImages.add(dbCursor.next().get("filename").toString());
        }
        return listAllImages;
	}
	
	public void save(BufferedImage bufferedImage, String imgName) throws IOException {
		String extension = env.getProperty(EXTENSION);
		String groupName = env.getProperty(GROUP_DATABASE);
		byte[] binaryImage = ImageConverter.convertBuffImageToByteArray(bufferedImage, extension);
        GridFS fs = new GridFS(mongoDB, groupName);
        GridFSInputFile in = fs.createFile(binaryImage);
        in.setFilename(imgName);
        in.save();
	}
}
