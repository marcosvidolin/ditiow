package com.vidolima.ditiow.util;

import java.util.Collection;
import org.springframework.http.ResponseEntity;

public final class ResponseEntityUtil {

  /**
   * Converts the body response into an instance of the classOfBody.
   *
   * @param response {@link ResponseEntity}
   * @param classOfBody the class of the new instance of the response body
   * @return ResponseEntity
   */
  public static ResponseEntity<?> convertBody(final ResponseEntity<?> response, final Class<?> classOfBody) {
    Object copied = null;
    if (response.getBody() instanceof Collection<?>) {
      copied = CopyPropertiesHelper.createCopy((Collection<?>) response.getBody(), classOfBody);
    } else {
      copied = CopyPropertiesHelper.createCopy(response.getBody(), classOfBody);
    };
    return new ResponseEntity(copied, response.getHeaders(), response.getStatusCode());
  }

}
