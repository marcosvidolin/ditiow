package com.vidolima.ditiow.assembler;

import com.vidolima.ditiow.BaseTest;
import com.vidolima.ditiow.aspect.util.ResponseEntityUtil;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

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
