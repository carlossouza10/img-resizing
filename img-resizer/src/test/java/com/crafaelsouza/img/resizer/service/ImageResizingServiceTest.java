package com.crafaelsouza.img.resizer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.crafaelsouza.img.resizer.repository.ImageRepositoryImpl;

public class ImageResizingServiceTest {

	@Mock
	private ImageRepositoryImpl imageRepository;
	
	@InjectMocks
	private ImageResizingServiceImpl imgResizingService;
	
	@Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetAllImagesNames() {
		List<String> list = new ArrayList<>();
		list.add("img1");
		list.add("img1");
		Mockito.doReturn(list).when(imageRepository).findAllImagesName();
		List<String> allImagesNames = imgResizingService.getAllImagesNames();
		Assert.assertNotNull(allImagesNames);
		Assert.assertTrue(allImagesNames.containsAll(list));
	}
	
	@Test
	public void testGetAllImagesNames1() {
		byte[] imgExpected = new byte[]{1,2,3};
		Mockito.doReturn(imgExpected).when(imageRepository).findImage("123");
		byte[] img = imgResizingService.findImage("123");
		Assert.assertNotNull(img);
		Assert.assertTrue(Arrays.asList(img).containsAll(Arrays.asList(imgExpected)));
	}
	
}
