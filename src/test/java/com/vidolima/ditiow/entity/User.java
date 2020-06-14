package com.vidolima.ditiow.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class User implements Serializable {

  private static final long serialVersionUID = -6026783207468034654L;
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String password;
  private String token;
  private Double Salary;
  private List<String> comments;
  private Boolean gender;
  private Address address;
  private List<Point> points;
  private Map<String, Address> addressByState;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Double getSalary() {
    return Salary;
  }

  public void setSalary(Double salary) {
    Salary = salary;
  }

  public List<String> getComments() {
    return comments;
  }

  public void setComments(List<String> comments) {
    this.comments = comments;
  }

  public Boolean getGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
  }

  public Map<String, Address> getAddressByState() {
    return addressByState;
  }

  public void setAddressByState(Map<String, Address> addressByState) {
    this.addressByState = addressByState;
  }
}
