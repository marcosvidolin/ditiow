package com.vidolima.ditiow.resource;

/**
 * Resource interface that defines the contract to convert a resource into
 *  * a domain class.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Jan 16, 2019
 */
public interface Resource {

  /**
   * Returns a converted object of the "B" type.
   *
   * @param <B> the type of domain class
   * @return the object
   */
  <B> B toDomain();

}
