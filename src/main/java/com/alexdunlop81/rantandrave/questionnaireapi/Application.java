/**
 * 
 */
package com.alexdunlop81.rantandrave.questionnaireapi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Alex
 *
 */
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer{
	private static final Logger logger = LogManager.getLogger("Application");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//logger.info("Starting server");
		SpringApplication.run(Application.class, args);
		logger.info("Running server");

	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
