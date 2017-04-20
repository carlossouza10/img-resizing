package com.crafaelsouza.img.resizer.rest;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crafaelsouza.img.resizer.service.ImageResizingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@RestController
public class ImageRestController {

	@Autowired
	private ImageResizingService imageResizingService;
	
	private static String APPLICATION_URL = "localhost:8080/images/";
	
	@RequestMapping(value = "/images/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] displayImage(@PathVariable String name) {
        return imageResizingService.findImage(name);
    }

	@RequestMapping(value = "/images", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getImages() throws JSONException {
		List<String> imageNames = imageResizingService.getAllImagesNames();
		JSONArray imageJsonArray = getImageNamesAsJSONArray(imageNames);
		JSONObject jsonObject = createImagesJSONObject(imageJsonArray);
        return getPrettyJson(jsonObject);
	}
	
	@RequestMapping(value = "/generate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String generateResizedImages() throws IOException, JSONException {
		imageResizingService.generateResizedImages();
		return getImages();
    }
	
    private String getPrettyJson(JSONObject obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(obj.toString());
        return gson.toJson(jsonElement);
    }
    
    private JSONObject createImagesJSONObject(JSONArray jsonArray) throws JSONException{
        JSONObject obj = new JSONObject();

        if (jsonArray.length() > 0){
            obj.accumulate("images", jsonArray);
        }

        return obj;
    }
    
    private JSONArray getImageNamesAsJSONArray(List<String> imageNames) throws JSONException {
        JSONArray namesArray = new JSONArray();

        for (String name : imageNames){
            JSONObject img = new JSONObject();
            img.put("url", APPLICATION_URL + name);
            namesArray.put(img);
        }

        return namesArray;
    }
}
