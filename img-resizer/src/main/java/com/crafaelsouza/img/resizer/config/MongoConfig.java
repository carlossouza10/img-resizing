package com.crafaelsouza.img.resizer.config;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * The Class MongoConfig responsible for setting up mongo configuration
 * 
 * @Carlos Souza
 */
@Configuration
@EnableMongoRepositories(basePackages={"com.crafaelsouza.resizerimg"})
@ComponentScan(basePackages = {"com.crafaelsouza.resizerimg.repository"})
@PropertySource(value = "classpath:application.properties")
public class MongoConfig {

	@Value("${mongo.database}")
	private String database;
	
	@Value("${mongo.host}")
	private String host;
	
	@Value("${mongo.port}")
	private Integer port;
	
	@Value("${mongo.username}")
	private String username;
	
	@Value("${mongo.password}")
	private String password;

	protected String getDatabaseName() {
		return "resizerimg";
	}

	@Bean
	public MongoClient getMongoClient() throws Exception {
		MongoClient mongoClient = new MongoClient(getServerAddress(), getCredentials());
		return mongoClient;
	}
	
	@Bean
	public DB getDB() throws Exception {
		MongoClient mongoClient = getMongoClient();
		return mongoClient.getDB(getDatabaseName());
	}
	
	private List<ServerAddress> getServerAddress() throws UnknownHostException {
		ServerAddress server = new ServerAddress(host, port);
		List<ServerAddress> serverList = Arrays.asList(server);
		return serverList;
	}

	private List<MongoCredential> getCredentials() {
		MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		credentials.add(mongoCredential);
		return credentials;
	}
}