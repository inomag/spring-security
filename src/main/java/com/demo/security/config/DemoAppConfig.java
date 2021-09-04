package com.demo.security.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.demo.security")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	
	
	// SETUP VARIABLE TO HOLD PROPERTIES
	@Autowired
	private Environment env;
	
	// SETUP LOGGER FOR DIAGNOSTICS
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	// DEFINE A BEAN FOR VIEWRESOLVER
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	// DEFINE A BEAN FOR OUR SECURITY DATASOURCE
	
	@Bean
	public DataSource securityDataSource() {
		// CREATE CONNECTION POOL
		ComboPooledDataSource securitySource
			= new ComboPooledDataSource();
		
		// SET JDBC DRIVER
		try {
			securitySource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		// LOG CONNECTION PROPS
		logger.info(">>>>> jdbc.url = "+env.getProperty("jdbc.url"));
		logger.info(">>>>> jdbc.user = "+env.getProperty("jdbc.user"));
		
		
		// SET DATABASE CONNECTION PROPS
		securitySource.setJdbcUrl(env.getProperty("jdbc.url"));
		securitySource.setUser(env.getProperty("jdbc.user"));
		securitySource.setPassword(env.getProperty("jdbc.password"));
		
		
		// SET CONNECTION POOL PROPS
		securitySource.setInitialPoolSize(getIntProp("connection.pool.initialPoolSize"));
		securitySource.setMinPoolSize(getIntProp("connection.pool.minPoolSize"));
		securitySource.setMaxPoolSize(getIntProp("connection.pool.maxPoolSize"));
		securitySource.setMaxIdleTime(getIntProp("connection.pool.maxIdleTime"));
		
		
		return securitySource;
	}
	
	// NEED A HELPER METHOD
	// READ ENVIRONMENT PROPERTY & CONVERT TO INT
	private int getIntProp(String prop) {
		String val = env.getProperty(prop);
		return Integer.parseInt(val);
	}

}
