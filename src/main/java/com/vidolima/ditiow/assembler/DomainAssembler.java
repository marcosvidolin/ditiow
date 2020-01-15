package com.vidolima.ditiow.assembler;

import com.vidolima.ditiow.assembler.util.ReflectionUtil;
import com.vidolima.ditiow.exception.IllegalCopyException;
import com.vidolima.ditiow.resource.AbstractResource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * An assembler class to converts an {@link AbstractResource} into a domain.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Jan 13, 2020
 */
public final class DomainAssembler extends AbstractAsselbler {

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

    T targetObject = createCopy(object, classOfTargetObject);

    // TODO: refactor

    Field[] targetObjectFields = targetObject.getClass().getDeclaredFields();
    for (Field targetObjectField : targetObjectFields) {
      Field resourceField = getResourceFieldByName(targetObjectField.getName(), object);
      if (resourceField != null) {
        try {
          Object obj = ReflectionUtil.getFieldValue(resourceField.getName(), object);
          if (obj != null) {
            if (ReflectionUtil.isCollection(obj)) {
              Collection<?> resources = (Collection<?>) obj;
              Collection<Object> domains = new ArrayList<>();
              for (int i = 0; i < resources.size(); i++) {
                Object o = ((AbstractResource) resources.toArray()[i]).toDomain();
                domains.add(o);
              }
              targetObjectField.setAccessible(true);
              targetObjectField.set(targetObject, domains);
            } else {
              Object value = ((AbstractResource) obj).toDomain();
              targetObjectField.setAccessible(true);
              targetObjectField.set(targetObject, value);
            }
          }
        } catch (IllegalAccessException | NoSuchFieldException e) {
          throw new IllegalCopyException("Could not copy some attribute values.", e);
        }
      }
    }
    return targetObject;
  }

}
