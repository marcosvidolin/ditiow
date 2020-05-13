package com.vidolima.ditiow.annotation;

import java.lang.annotation.*;

/**
 * Method level annotation to turn on automatic object conversion of a method parameter (resource).
 * Adding it to method enables conversion for it.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Dez 23, 2019
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseResource {

  /**
   * The class type that will be used to convert the response.
   * @return Class
   */
  Class<?> value();

}
