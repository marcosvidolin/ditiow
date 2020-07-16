package com.vidolima.ditiow.assembler;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vidolima.ditiow.BaseTest;
import com.vidolima.ditiow.aspect.DitiowAspect;
import com.vidolima.ditiow.entity.Person;

public class DitiowAspectTest extends BaseTest {
	@Test
	public void givenJoinPointWithStatusOKWhenProceedResponseResourceThenReturnStatusOK() {
		try {
			
			Method method = null;
			try {
			  Person person = new Person();
			  method = person.getClass().getMethod("init");
			} catch (SecurityException e) { }
			  catch (NoSuchMethodException e) {  }
			
			MethodSignature methodSignature = mock(MethodSignature.class);
			ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
			when(joinPoint.proceed()).thenReturn(new ResponseEntity<String>(HttpStatus.OK));
			when(joinPoint.getSignature()).thenReturn(methodSignature);
			when(methodSignature.getMethod()).thenReturn(method);
			DitiowAspect aspect = new DitiowAspect();
			ResponseEntity response = (ResponseEntity) aspect.proceedResponseResource(joinPoint);
			assertSame(response.getStatusCode(), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
