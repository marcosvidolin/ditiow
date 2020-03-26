package com.vidolima.ditiow.entity;

import java.io.Serializable;
import java.util.Map;

public class Address implements Serializable {

  private static final long serialVersionUID = 5881657496459429087L;
  private String streetName;
  private String country;
  private Map<String, Integer> map;

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Map<String, Integer> getMap() {
    return map;
  }

  public void setMap(Map<String, Integer> map) {
    this.map = map;
  }
}
