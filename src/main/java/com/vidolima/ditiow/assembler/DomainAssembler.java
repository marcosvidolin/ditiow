package com.vidolima.ditiow.assembler;

import com.vidolima.ditiow.assembler.util.ReflectionUtil;
import com.vidolima.ditiow.exception.IllegalCopyException;
import com.vidolima.ditiow.resource.AbstractResource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * An assembler class to converts an {@link AbstractResource} into a domain.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Jan 13, 2020
 */
public final class DomainAssembler extends AbstractAssembler {

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param object object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @return T
   */
  @Override
  public <T> T assembly(final Object object, final Class<T> classOfTargetObject) {

    if (object == null || classOfTargetObject == null) {
      return null;
    }

    T targetObject = createCopy(object, classOfTargetObject);

    Field[] targetObjectFields = targetObject.getClass().getDeclaredFields();
    for (Field targetObjectField : targetObjectFields) {
      Field resourceField = getResourceFieldByName(targetObjectField.getName(), object);
      if (resourceField != null) {
        try {
          Object obj = ReflectionUtil.getFieldValue(resourceField.getName(), object);
          if (obj != null) {
            if (ReflectionUtil.isCollection(obj)) {
              this.assemblyCollection(targetObject, targetObjectField, (Collection<?>) obj);
            } else if (ReflectionUtil.isMap(obj)) {
              this.assemblyMap(targetObject, targetObjectField, (Map<?, ?>) obj);
            } else {
              this.assemblyResource(targetObject, targetObjectField, (AbstractResource<?>) obj);
            }
          }
        } catch (IllegalAccessException | NoSuchFieldException e) {
          throw new IllegalCopyException("Could not copy some attribute values.", e);
        }
      }
    }
    return targetObject;
  }

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param targetObject
   * @param targetObjectField
   * @param obj
   * @param <T> return type
   * @throws IllegalAccessException
   */
  private <T> void assemblyResource(T targetObject, Field targetObjectField, AbstractResource obj)
          throws IllegalAccessException {
    Object value = obj.toDomain();
    targetObjectField.setAccessible(true);
    targetObjectField.set(targetObject, value);
  }

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param targetObject
   * @param targetObjectField
   * @param obj
   * @param <T> return type
   * @throws IllegalAccessException
   */
  private <T> void assemblyCollection(T targetObject, Field targetObjectField, Collection<?> obj)
          throws IllegalAccessException {
    Collection<?> resources = obj;
    Collection<Object> domains = new ArrayList<>();
    for (int i = 0; i < resources.size(); i++) {
      Object o = ((AbstractResource<?>) resources.toArray()[i]).toDomain();
      domains.add(o);
    }
    targetObjectField.setAccessible(true);
    targetObjectField.set(targetObject, domains);
  }

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param targetObject
   * @param targetObjectField
   * @param obj
   * @param <T> return type
   * @throws IllegalAccessException
   */
  private <T> void assemblyMap(T targetObject, Field targetObjectField, Map<?, ?> obj)
          throws IllegalAccessException {
  }

}
