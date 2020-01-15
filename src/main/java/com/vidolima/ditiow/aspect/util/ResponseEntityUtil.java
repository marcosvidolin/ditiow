package com.vidolima.ditiow.util;

import com.vidolima.ditiow.assembler.ResourceAssembler;
import org.springframework.http.ResponseEntity;

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
    Object copied = ResourceAssembler.copy(response.getBody(), classOfBody);
    return new ResponseEntity(copied, response.getHeaders(), response.getStatusCode());
  }

}
