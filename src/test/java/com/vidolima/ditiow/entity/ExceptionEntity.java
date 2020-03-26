package com.vidolima.ditiow.entity;

import java.io.Serializable;

public class ExceptionEntity implements Serializable {

  private static final long serialVersionUID = -5325919554720490679L;
  private String entity;

  public ExceptionEntity() {
    throw new IllegalArgumentException("IllegalArgumentException");
  }

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }
}
