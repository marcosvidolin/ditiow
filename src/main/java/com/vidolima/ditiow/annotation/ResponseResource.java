package com.vidolima.ditiow.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Method level annotation to turn on automatic object conversion of a method parameter (resource).
 * Adding it to method enables conversion for it.
 *
 * @author mvidolin
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
