package com.crafaelsouza.img.resizer.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crafaelsouza.img.resizer.config.TestContextConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes={TestContextConfiguration.class})
@PropertySource("classpath:application-test.properties ")
public class ImageResizerImplTest {

	private BufferedImage imageTest;
	
	@Autowired
	private ImageResizer resizer;

	@Before
	public void setup() throws IOException {
		imageTest = ImageIO.read(new File(System.getProperty("user.dir") + "/src/test/resources/img_test.jpg")); 
	}

	@Test
	public void testResizeImageSmall() throws Exception {
		BufferedImage smallImg = resizer.resizeImageSmall(imageTest);
		Assert.assertEquals(320, smallImg.getWidth());
		Assert.assertEquals(240, smallImg.getHeight());
	}
	
	@Test
	public void testResizeImageMedium() throws Exception {
		BufferedImage smallImg = resizer.resizeImageMedium(imageTest);
		Assert.assertEquals(384, smallImg.getWidth());
		Assert.assertEquals(288, smallImg.getHeight());
	}
	
	@Test
	public void testResizeImageLarge() throws Exception {
		BufferedImage smallImg = resizer.resizeImageLarge(imageTest);
		Assert.assertEquals(640, smallImg.getWidth());
		Assert.assertEquals(480, smallImg.getHeight());
	}

}