package com.vidolima.ditiow.aspect;

import com.vidolima.ditiow.annotation.ResponseResource;
import com.vidolima.ditiow.aspect.util.ResponseEntityUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;

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
   * @param proceedingJoinPoint the join point of the aspect
   * @return Object after conversion
   * @throws Throwable generic error
   */
  @Around("com.vidolima.ditiow.aspect.CommonJoinPointConfig.responseResourceExecution()")
  public Object proceedResponseResource(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    ResponseEntity<?> response = (ResponseEntity<?>) proceedingJoinPoint.proceed();
    ResponseResource annotation = this.getAnnotation(proceedingJoinPoint, ResponseResource.class);
    Class<?> classOfResponse = this.getAnnotationValue(annotation);
    String[] ignoredProperties = this.getAnnotationIgnoredProperties(annotation);
    return ResponseEntityUtil.convertBody(response, classOfResponse, ignoredProperties);
  }

  /**
   * Gets the annotation from the joinPoint.
   *
   * @param joinPoint {@link ProceedingJoinPoint}
   * @param classOfAnnotation the class of the annotation
   * @return the annotation
   */
  private ResponseResource getAnnotation(final ProceedingJoinPoint joinPoint, final Class classOfAnnotation) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    return (ResponseResource) method.getAnnotation(classOfAnnotation);
  }

  /**
   * Gets the value from the annotation.
   *
   * @param annotation ResponseResource
   * @return the annotation value
   */
  private Class<?> getAnnotationValue(final ResponseResource annotation) {
    return annotation.value();
  }

  /**
   * Gets the ignored fields value from the annotation.
   *
   * @param annotation ResponseResource
   * @return the annotation ignore field value
   */
  private String[] getAnnotationIgnoredProperties(final ResponseResource annotation) {
    return annotation.ignoreProperties();
  }

}
