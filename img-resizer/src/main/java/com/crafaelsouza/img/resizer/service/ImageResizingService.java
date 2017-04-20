package com.crafaelsouza.img.resizer.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

public interface ImageResizingService {

	public List<String> getAllImagesNames();

	public byte[] findImage(String name);

	public void generateResizedImages() throws JSONException, IOException;
}
