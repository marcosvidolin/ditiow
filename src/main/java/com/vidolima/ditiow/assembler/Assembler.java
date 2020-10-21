package com.vidolima.ditiow.assembler;

/**
 * Defines an Assembler object.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Jan 14, 2020
 */
public interface Assembler {

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param object object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @return T
   */
  <T> T assembly(Object object, Class<T> classOfTargetObject);

  /**
   * Return a new object (instance of "T") with all values copied from the the given object.
   *
   * @param object object to be copied
   * @param classOfTargetObject the class of the target object
   * @param <T> return type
   * @param ignoreProperties array of fields to be ignored
   * @return T
   */
  <T> T assembly(Object object, Class<T> classOfTargetObject, String[] ignoreProperties);

}
