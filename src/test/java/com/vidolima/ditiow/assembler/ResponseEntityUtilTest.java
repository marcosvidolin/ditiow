package com.vidolima.ditiow.assembler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vidolima.ditiow.BaseTest;
import com.vidolima.ditiow.annotation.ResponseResource;
import com.vidolima.ditiow.aspect.CommonJoinPointConfig;
import com.vidolima.ditiow.aspect.DitiowAspect;
import com.vidolima.ditiow.aspect.util.ResponseEntityUtil;
import com.vidolima.ditiow.entity.Person;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class ResponseEntityUtilTest extends BaseTest {
	
	@Test
	public void givenStatusOKWhenConvertBodyThenReturnOK() {
		String message = "Anything";
		ResponseEntity<?> response = ResponseEntityUtil.convertBody(new ResponseEntity<String>(message, HttpStatus.OK),
				String.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void givenEmptyHeadersWhenConvertBodyThenReturnEmptyHeaders() {
		String message = "Anything";
		ResponseEntityUtil util = new ResponseEntityUtil();
		ResponseEntity<?> response = util.convertBody(new ResponseEntity<String>(message, HttpStatus.OK),
				String.class);
		assertEquals(response.getHeaders().isEmpty(), new HttpHeaders().isEmpty());
	}
	
}
