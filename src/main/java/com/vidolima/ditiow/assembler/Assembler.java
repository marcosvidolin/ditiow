package com.vidolima.ditiow.assembler;

public interface Assemblable {

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param object object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @return T
   */
  <T> T assembly(final Object object, final Class<T> classOfTargetObject);

}
