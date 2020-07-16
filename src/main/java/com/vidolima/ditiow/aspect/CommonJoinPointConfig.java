package com.vidolima.ditiow.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * A main class to store all the pointcuts.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Dez 23, 2019
 */
public class CommonJoinPointConfig {

  /**
   * Pointcut declaration to get all public methods with {@link com.vidolima.ditiow.annotation.ResponseResource}
   * annotation.
   */
  @SuppressWarnings("PMD.UncommentedEmptyMethodBody")
  @Pointcut("execution(public * *(..)) && @annotation(com.vidolima.ditiow.annotation.ResponseResource)")
  public void responseResourceExecution() {}

}