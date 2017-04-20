package com.crafaelsouza.img.resizer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ImageConfig {

	 @Value("${application.images.small.width}")
	 private Integer smallWidth;
	 
	 @Value("${application.images.small.height}")
	 private Integer smallHeight;
	 
	 @Value("${application.images.medium.width}")
	 private Integer mediumWidth;
	 
	 @Value("${application.images.medium.height}")
	 private Integer mediumHeight;
	 
	 @Value("${application.images.large.width}")
	 private Integer largeWidth;
	 
	 @Value("${application.images.large.height}")
	 private Integer largeHeight;

	public Integer getSmallWidth() {
		return smallWidth;
	}

	public void setSmallWidth(Integer smallWidth) {
		this.smallWidth = smallWidth;
	}

	public Integer getSmallHeight() {
		return smallHeight;
	}

	public void setSmallHeight(Integer smallHeight) {
		this.smallHeight = smallHeight;
	}

	public Integer getMediumWidth() {
		return mediumWidth;
	}

	public void setMediumWidth(Integer mediumWidth) {
		this.mediumWidth = mediumWidth;
	}

	public Integer getMediumHeight() {
		return mediumHeight;
	}

	public void setMediumHeight(Integer mediumHeight) {
		this.mediumHeight = mediumHeight;
	}

	public Integer getLargeWidth() {
		return largeWidth;
	}

	public void setLargeWidth(Integer largeWidth) {
		this.largeWidth = largeWidth;
	}

	public Integer getLargeHeight() {
		return largeHeight;
	}

	public void setLargeHeight(Integer largeHeight) {
		this.largeHeight = largeHeight;
	}
	 
}
