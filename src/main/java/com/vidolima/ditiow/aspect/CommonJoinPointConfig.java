package com.vidolima.ditiow.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * A main class to store all the pointcuts.
 *
 * @author mvidolin
 * @since Dez 23, 2019
 */
public class CommonJoinPointConfig {

  /**
   * Pointcut declaration to get all public methods with {@link com.vidolima.ditiow.annotation.ResponseResource}
   * annotation.
   */
  @Pointcut("execution(public * *(..)) && @annotation(com.vidolima.ditiow.annotation.ResponseResource)")
  public void responseResourceExecution() {}

}
