package com.vidolima.ditiow.assembler;

import com.vidolima.ditiow.assembler.util.ReflectionUtil;
import com.vidolima.ditiow.exception.IllegalCopyException;
import com.vidolima.ditiow.resource.AbstractResource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.BeanUtils;

/**
 * Provides all default methods for an {@link Assembler}.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Jan 14, 2020
 */
public abstract class AbstractAsselbler implements Assembler {

  /**
   * Gets the resource's field by a given name.
   *
   * @param fieldName field name
   * @param object target object
   * @return returns a {@link Field} or null if not found
   */
  protected Field getResourceFieldByName(final String fieldName, final Object object) {
    try {
      Field field = object.getClass().getDeclaredField(fieldName);
      if (ReflectionUtil.isFieldTypeOf(field, AbstractResource.class)) {
        return field;
      }
    } catch (NoSuchFieldException e) {
      return null;
    }
    return null;
  }

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param obj object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @return T
   */
  protected <T> T createCopy(final Object obj, final Class<T> classOfTargetObject) {
    if (obj == null) {
      return null;
    }

    if (obj instanceof Collection<?>) {
      return (T) createCollectionCopy((Collection<?>) obj, classOfTargetObject);
    }

    try {
      T targetObject = classOfTargetObject.newInstance();
      BeanUtils.copyProperties(obj, targetObject);
      return targetObject;
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IllegalCopyException("Could not create new instance of the target class.", e);
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
  protected <T> Collection<T> createCollectionCopy(Collection<?> objs, final Class<T> classOfTargetObject) {
    if (objs == null) {
      return null;
    }
    Collection<T> targetObject = new ArrayList<>();
    for (Object obj : objs) {
      targetObject.add(this.assembly(obj, classOfTargetObject));
    }
    return targetObject;
  }

}
