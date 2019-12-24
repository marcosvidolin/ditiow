package com.vidolima.ditiow.util;

import com.vidolima.copypropertiesassembler.exception.IllegalAssemblerConversionException;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.BeanUtils;

/**
 * A helper class to copy properties from an object.
 *
 * @author mvidolin
 * @since Dez 23, 2019
 */
public final class CopyPropertiesHelper {

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param obj object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @return T
   */
  public static <T> T createCopy(final Object obj, final Class<T> classOfTargetObject) {
    if (obj == null) {
      return null;
    }
    try {
      T targetObject = classOfTargetObject.newInstance();
      BeanUtils.copyProperties(obj, targetObject);
      return targetObject;
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IllegalAssemblerConversionException("Could not create new instance of the target class.", e);
    }
  }

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param objs object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @return T
   */
  public static <T> Collection<T> createCopy(Collection<?> objs, final Class<T> classOfTargetObject) {
    if (objs == null) {
      return null;
    }
    Collection<T> targetObject = new ArrayList<>();
    for (Object obj : objs) {
      targetObject.add(createCopy(obj, classOfTargetObject));
    }
    return targetObject;
  }

}
