package com.vidolima.ditiow.aspect.util;

import org.springframework.http.ResponseEntity;

import com.vidolima.ditiow.assembler.Assembler;
import com.vidolima.ditiow.assembler.ResourceAssembler;

/**
 * Utility class used to prepare the HTTP Response.
 *
 * @author Marcos A. Vidolin de Lima
 */
public final class ResponseEntityUtil {

  /**
   * Converts the body response into an instance of the classOfBody.
   *
   * @param response {@link ResponseEntity}
   * @param classOfBody the class of the new instance of the response body
   * @return ResponseEntity
   */
  public static ResponseEntity<?> convertBody(final ResponseEntity<?> response, final Class<?> classOfBody) {
    Assembler assembler = new ResourceAssembler();
    Object copied = assembler.assembly(response.getBody(), classOfBody);
    return new ResponseEntity(copied, response.getHeaders(), response.getStatusCode());
  }

  /**
   * Converts the body response into an instance of the classOfBody.
   *
   * @param response {@link ResponseEntity}
   * @param classOfBody the class of the new instance of the response body
   * @return ResponseEntity
   */
  public static ResponseEntity<?> convertBody(final ResponseEntity<?> response, final Class<?> classOfBody
          , final String[] excludedFields) {
    Assembler assembler = new ResourceAssembler();
    Object copied = assembler.assembly(response.getBody(), classOfBody, excludedFields);
    return new ResponseEntity(copied, response.getHeaders(), response.getStatusCode());
  }

}
