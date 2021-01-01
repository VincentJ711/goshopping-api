package com.revature.goshopping.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.revature.goshopping.exception.ServiceException;

@Aspect
@Component
public class AspectUtility {
	private static Logger logger = LogManager.getLogger(AspectUtility.class);

	@Before("execution(* com.revature.goshopping.controller..*.*(..))")
	public void logBeforeExec(JoinPoint joinPoint) {
		logger.info(joinPoint.getSignature().getName());
	}

	@AfterThrowing(pointcut = ("execution(* com.revature.goshopping.service..*.*(..))"), throwing = "e")
	public void logOnThrow(ServiceException e) {
		if (e.status == HttpStatus.INTERNAL_SERVER_ERROR) {
			logger.error("Interal Server Error");
			logger.error(e.toString());
			e.printStackTrace();
		} else {
			logger.warn(e.toString());
		}
	}
	
	@AfterThrowing(pointcut = ("execution(* com.revature.goshopping.service..*.*(..))"), throwing = "e")
	public void logOnThrow(Throwable e) {
		if(!(e instanceof ServiceException)) {
			logger.error("Internal Server Error");
			logger.error(e.toString());
			e.printStackTrace();
		}
	}

}
