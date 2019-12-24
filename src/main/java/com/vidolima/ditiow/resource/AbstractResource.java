package com.vidolima.ditiow.resource;

import com.vidolima.ditiow.util.CopyPropertiesHelper;

/**
 * Abstract resource class that defines the contract to convert a resource into
 * a business class.
 *
 * @author mvidolin
 * @since Dez 23, 2019
 * @param <B>
 */
public abstract class AbstractResource<B> {

  private Class<B> businessClass;

  public AbstractResource(Class<B> businessClass) {
    this.businessClass = businessClass;
  }

  /**
   *
   * @param <B>
   * @return
   */
  public <B> B toBusiness() {
    return (B) CopyPropertiesHelper.createCopy(this, businessClass);
  }

}
