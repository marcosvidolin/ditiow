package com.vidolima.ditiow.assembler;

import com.vidolima.ditiow.exception.IllegalCopyException;
import com.vidolima.ditiow.resource.AbstractResource;
import com.vidolima.ditiow.assembler.util.ReflectionUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.BeanUtils;

/**
 * A helper class to copy properties from an object.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Dez 23, 2019
 */
public final class CopyPropertiesHelper {

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param object object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @return T
   */
  public static <T> T copy(final Object object, final Class<T> classOfTargetObject) {

    T copy = createCopy(object, classOfTargetObject);

    Field[] fields = copy.getClass().getDeclaredFields();
    for (Field field : fields) {
      Class<?> fieldGenericType = ReflectionUtil.getFieldGenricType(field);
      if (ReflectionUtil.isFieldTypeOf(field, AbstractResource.class)) {
        try {
          Object value = ReflectionUtil.getFieldValue(field.getName(), object);
          T innerCopy = (T) createCopy(value, fieldGenericType);
          field.setAccessible(true);
          field.set(copy, innerCopy);
        } catch (IllegalAccessException | NoSuchFieldException e) {
          // TODO: ?
        }
      }
    }
    return copy;
  }

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param obj object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @return T
   */
  protected static <T> T createCopy(final Object obj, final Class<T> classOfTargetObject) {
    if (obj == null) {
      return null;
    }

    if (obj instanceof Collection<?>) {
      return (T) createCopy((Collection<?>) obj, classOfTargetObject);
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
  protected static <T> Collection<T> createCopy(Collection<?> objs, final Class<T> classOfTargetObject) {
    if (objs == null) {
      return null;
    }
    Collection<T> targetObject = new ArrayList<>();
    for (Object obj : objs) {
      targetObject.add(copy(obj, classOfTargetObject));
    }
    return targetObject;
  }

}
