package com.vidolima.ditiow.resource;

import com.vidolima.ditiow.entity.ValueException;

import java.io.Serializable;

public class ValueExceptionResource extends AbstractResource<ValueException>
    implements Serializable {

  private static final long serialVersionUID = 2395255090557984799L;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getValue() {
    throw new IllegalArgumentException("IllegalArgumentException");
  }

  public void setValue(Integer value) {
    throw new IllegalArgumentException("IllegalArgumentException");
  }
}
