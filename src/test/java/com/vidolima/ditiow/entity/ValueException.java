package com.vidolima.ditiow.entity;

import java.io.Serializable;

public class ValueException implements Serializable {

  private static final long serialVersionUID = -8620736583570371538L;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setValue(Integer value) {
    throw new IllegalArgumentException("IllegalArgumentException");
  }

  public Integer getValue() {
    throw new IllegalArgumentException("IllegalArgumentException");
  }
}
