package com.vidolima.ditiow.assembler;

import com.vidolima.ditiow.assembler.util.ReflectionUtil;
import com.vidolima.ditiow.exception.IllegalCopyException;
import com.vidolima.ditiow.resource.AbstractResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides all default methods for an {@link Assembler}.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Jan 14, 2020
 */
public abstract class AbstractAssembler implements Assembler {

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
   * @param ignoreProperties properties to be ignored
   * @param <T> return type
   * @return T
   */
  protected <T> T createCopy(final Object obj, final Class<T> classOfTargetObject
          , final String[] ignoreProperties) {

    if (obj == null) {
      return null;
    }

    if (obj instanceof Collection<?>) {
      return (T) createCollectionCopy((Collection<?>) obj, classOfTargetObject, ignoreProperties);
    }

    try {
      T targetObject = classOfTargetObject.getDeclaredConstructor().newInstance();
      BeanUtils.copyProperties(obj, targetObject, ignoreProperties);
      return targetObject;
    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
            | InvocationTargetException | FatalBeanException e) {
      throw new IllegalCopyException("Could not create new instance of the target class.", e);
    }
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
    return this.createCopy(obj, classOfTargetObject, null);
  }

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param objs object to be copied
   * @param classOfTargetObject the class of the target object
   * @param ignoreProperties properties to be ignored
   * @param <T> return type
   * @return T
   */
  protected <T> Collection<T> createCollectionCopy(Collection<?> objs, final Class<T> classOfTargetObject
          , final String[] ignoreProperties) {

    if (objs == null) {
      return null;
    }

    Collection<T> targetObject = new ArrayList<>();

    for (Object obj : objs) {
      targetObject.add(this.assembly(obj, classOfTargetObject, ignoreProperties));
    }
    return targetObject;
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
    return this.createCollectionCopy(objs, classOfTargetObject, null);
  }

}
