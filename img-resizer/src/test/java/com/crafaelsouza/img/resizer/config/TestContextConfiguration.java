package com.crafaelsouza.img.resizer.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan("com.crafaelsouza.img.resizer")
public class TestContextConfiguration {

    // this bean will be injected into the OrderServiceTest class
	@Bean
	public PropertyPlaceholderConfigurer propConfig() {
		PropertyPlaceholderConfigurer ppc =  new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("application-test.properties"));
		return ppc;
	}
}
