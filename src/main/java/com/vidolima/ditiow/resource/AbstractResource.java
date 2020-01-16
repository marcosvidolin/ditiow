package com.vidolima.ditiow.resource;

import com.vidolima.ditiow.assembler.Assembler;
import com.vidolima.ditiow.assembler.DomainAssembler;
import java.lang.reflect.ParameterizedType;

/**
 * Abstract resource class that defines the contract to convert a resource into
 * a domain class.
 *
 * @author Marcos A. Vidolin de Lima
 * @since Dez 23, 2019
 * @param <B>
 */
public abstract class AbstractResource<B> implements Resource {

  private Class<B> domainClass;

  /**
   * Default constructor.
   */
  public AbstractResource() {
    this.domainClass = ((Class<B>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0]);
  }

  /**
   * Returns a converted object of the "B" type.
   *
   * @param <B> the type of domain class
   * @return the object
   */
  public <B> B toDomain() {
    Assembler assembler = new DomainAssembler();
    return (B) assembler.assembly(this, domainClass);
  }

}
