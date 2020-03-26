package com.vidolima.ditiow.resource;

import com.vidolima.ditiow.entity.Point;
import com.vidolima.ditiow.entity.User;

import java.io.Serializable;
import java.util.List;

public class UserGetResource extends AbstractResource<User> implements Serializable {

  private static final long serialVersionUID = -8801571306359572676L;
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private Double Salary;
  private List<String> comments;
  private Boolean gender;
  private AddressResource address;
  private List<Point> points;

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

  public AddressResource getAddress() {
    return address;
  }

  public void setAddress(AddressResource address) {
    this.address = address;
  }

  public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
  }
}
