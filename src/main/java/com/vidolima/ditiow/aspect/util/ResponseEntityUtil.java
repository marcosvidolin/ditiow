package com.vidolima.ditiow.aspect.util;

import com.vidolima.ditiow.assembler.Assembler;
import com.vidolima.ditiow.assembler.ResourceAssembler;
import com.vidolima.ditiow.exception.InvalidResourceException;
import com.vidolima.ditiow.resource.Resource;
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
    if (!classOfBody.isAssignableFrom(Resource.class)) {
      throw new InvalidResourceException(classOfBody);
    }
    Assembler assembler = new ResourceAssembler();
    Object copied = assembler.assembly(response.getBody(), classOfBody);
    return new ResponseEntity(copied, response.getHeaders(), response.getStatusCode());
  }

}
