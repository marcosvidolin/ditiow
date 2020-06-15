package com.vidolima.ditiow.resource;

import com.vidolima.ditiow.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserCreateResource extends AbstractResource<User> implements Serializable {

  private static final long serialVersionUID = 6984502787199155285L;
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private Double Salary;
  private List<String> comments;
  private Boolean gender;
  private String password;
  private AddressResource address;
  private List<PointResource> points;
  private Map<String, AddressResource> addressByState;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AddressResource getAddress() {
    return address;
  }

  public void setAddress(AddressResource address) {
    this.address = address;
  }

  public List<PointResource> getPoints() {
    return points;
  }

  public void setPoints(List<PointResource> points) {
    this.points = points;
  }

  public Map<String, AddressResource> getAddressByState() {
    return addressByState;
  }

  public void setAddressByState(Map<String, AddressResource> addressByState) {
    this.addressByState = addressByState;
  }
}
