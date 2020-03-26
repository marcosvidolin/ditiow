package com.vidolima.ditiow.resource;

import com.vidolima.ditiow.entity.Address;

import java.io.Serializable;

public class PointResource extends AbstractResource<Address> implements Serializable {

  private static final long serialVersionUID = 4831441988474377801L;
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
