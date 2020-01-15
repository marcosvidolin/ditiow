package com.vidolima.ditiow.aspect;

import com.vidolima.ditiow.annotation.ResponseResource;
import com.vidolima.ditiow.aspect.util.ResponseEntityUtil;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;

/**
 * An aspect class to intercept rest controllers methods and convert objects (POJOs)
 * into other objects according the value of the annotation.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Dez 23, 2019
 */
@Aspect
public final class DitiowAspect {

  /**
   * Convert the return of the method into a resource instance of the annotation value.
   *
   * @param proceedingJoinPoint
   * @return Object after conversion
   * @throws Throwable
   */
  // TODO: ver a melhor annotation para usar (Around)?
  @Around("com.vidolima.ditiow.aspect.CommonJoinPointConfig.responseResourceExecution()")
  public Object proceedResponseResource(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    ResponseEntity<?> response = (ResponseEntity<?>) proceedingJoinPoint.proceed();
    Class<?> classOfResponse = this.getAnnotationValue(proceedingJoinPoint, ResponseResource.class);
    return ResponseEntityUtil.convertBody(response, classOfResponse);
  }

  /**
   * Gets the annotation value from the joinPoint.
   *
   * @param joinPoint {@link ProceedingJoinPoint}
   * @param classOfAnnotation the class of the annotation
   * @return the annotation value
   */
  private Class<?> getAnnotationValue(final ProceedingJoinPoint joinPoint, final Class classOfAnnotation) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    ResponseResource annotation = (ResponseResource) method.getAnnotation(classOfAnnotation);
    return annotation.value();
  }

}
