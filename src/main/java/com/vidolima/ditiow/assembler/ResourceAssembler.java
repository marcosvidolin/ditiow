package com.vidolima.ditiow.assembler;

import com.vidolima.ditiow.assembler.util.ReflectionUtil;
import com.vidolima.ditiow.exception.IllegalCopyException;
import com.vidolima.ditiow.resource.AbstractResource;

import java.lang.reflect.Field;

/**
 * An assembler class to converts a domain into an {@link AbstractResource}.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Dez 23, 2019
 */
public final class ResourceAssembler extends AbstractAssembler {

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param object object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @return T
   */
  public <T> T assembly(final Object object, final Class<T> classOfTargetObject) {

    if (object == null || classOfTargetObject == null) {
      return null;
    }

    T copy = createCopy(object, classOfTargetObject);

    Field[] fields = copy.getClass().getDeclaredFields();
    for (Field field : fields) {
      Class<?> fieldGenericType = ReflectionUtil.getFieldGenericType(field);
      if (ReflectionUtil.isFieldTypeOf(field, AbstractResource.class)) {
        try {
          Object value = ReflectionUtil.getFieldValue(field.getName(), object);
          T innerCopy = (T) createCopy(value, fieldGenericType);
          field.setAccessible(true);
          field.set(copy, innerCopy);
        } catch (IllegalAccessException | NoSuchFieldException e) {
          throw new IllegalCopyException("Could not copy some attribute values.", e);
        }
      }
    }
    return copy;
  }
}
