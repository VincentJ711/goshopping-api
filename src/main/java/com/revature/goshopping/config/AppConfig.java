package com.revature.goshopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.goshopping.dao.UserDaoForLoginService;

@Configuration
public class AppConfig {
	
	@Bean
	public UserDaoForLoginService userDAO() {
		return new UserDaoForLoginService();
	}
}
