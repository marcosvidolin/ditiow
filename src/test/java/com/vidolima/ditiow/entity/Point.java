package com.vidolima.ditiow.entity;

import java.io.Serializable;

public class Point implements Serializable {

  private static final long serialVersionUID = 509904164497866548L;
  private String gift;
  private Double value;

  public String getGift() {
    return gift;
  }

  public void setGift(String gift) {
    this.gift = gift;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }
}
