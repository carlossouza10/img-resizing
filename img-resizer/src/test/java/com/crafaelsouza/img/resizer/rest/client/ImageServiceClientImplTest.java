package com.crafaelsouza.img.resizer.rest.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crafaelsouza.img.resizer.rest.client.ImageServiceClientImpl;

public class ImageServiceClientImplTest {

	  private ImageServiceClientImpl client;

	    @Before
	    public void before(){
	    	client = new ImageServiceClientImpl();
	    }

	    @Test
	    public void testRetrieveImagesAsJson() throws Exception {
	        String text = client.retrieveImagesAsJson("http://54.152.221.29/images.json");
	        Assert.assertNotNull(text);
	        Assert.assertTrue(text.contains("url"));
	    }

}
