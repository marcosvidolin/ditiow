package com.vidolima.ditiow.assembler.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import org.springframework.util.Assert;

/**
 * Utility class for reflection.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Jan 10, 2020
 */
public final class ReflectionUtil {

  /**
   * Gets the field value.
   *
   * @param fieldName field name reference
   * @param object object with the field and its value
   * @return the value of the given field
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   */
  public static Object getFieldValue(final String fieldName, final Object object)
      throws NoSuchFieldException, IllegalAccessException {
    Field field = object.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    return field.get(object);
  }

  /**
   * Checks if the field is assignable from the given class.
   *
   * @param field the field to be checked
   * @param clazz the class to compare
   * @return true if is assignable
   */
  public static boolean isFieldTypeOf(final Field field, final Class<?> clazz) {
    Class<?> type = ReflectionUtil.getFieldGenricType(field);
    if (type.isPrimitive()) {
      return false;
    }
    if (type.getSuperclass().isAssignableFrom(Object.class)) {
      return false;
    }
    return type.getSuperclass().isAssignableFrom(clazz);
  }

  /**
   * Gets the generic type (class) of a Field.
   *
   * @param field
   * @return the class of the field
   */
  public static Class<?> getFieldGenricType(final Field field) {
    int index = 0; // TODO: always zero?
    Assert.notNull(field, "Parameter 'field' must be not null!");
    Assert.state(index > -1, "Parameter 'index' must be > -1!");
    Type type = field.getGenericType();
    if(type instanceof ParameterizedType) {
      ParameterizedType ptype = (ParameterizedType) type;
      type = ptype.getActualTypeArguments()[index];
      if(type instanceof ParameterizedType) {
        return (Class<?>) ((ParameterizedType) type).getRawType();
      }else {
        return (Class<?>) type;
      }
    }else {
      return (Class<?>) type;
    }
  }

  /**
   * Checks if the object is instance of {@link Collection}.
   *
   * @param object to check
   * @return true if Collection
   */
  public static boolean isCollection(final Object object) {
    return object instanceof Collection<?>;
  }
}
