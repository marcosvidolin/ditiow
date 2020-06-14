package com.vidolima.ditiow;

import com.vidolima.ditiow.entity.Address;
import com.vidolima.ditiow.entity.User;
import com.vidolima.ditiow.resource.AddressResource;
import com.vidolima.ditiow.resource.PointResource;
import com.vidolima.ditiow.resource.UserCreateResource;
import com.vidolima.ditiow.resource.UserGetResource;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseTest {

  protected static final String FIRST_NAME = "first_name";
  protected static final String LAST_NAME = "last_name";
  protected static final String EMAIL = "test@email.com";
  protected static final String USERNAME = "username";
  protected static final String PASSWORD = "passwd";
  protected static final String TOKEN = "hiugyhjbsjfgw78h2j3rnkjbshdfyuuszf";
  protected static final String COMMENT_1 = "employee of the month";
  protected static final String COMMENT_2 = "I really recommend him";
  protected static final String STREET_NAME = "st 1 ks";
  protected static final String COUNTRY = "USA";
  protected static final String GIFT = "iPhone";
  protected static final String ONE_STRING = "one";
  protected static final Integer ONE_INTEGER = Integer.valueOf(1);
  protected static final Boolean MALE = Boolean.TRUE;
  protected static final Double SALARY = Double.valueOf(4000);
  protected static final Double POINT_VALUE = Double.valueOf(5);
  protected static final String STATE_CODE = "HI";

  @Rule public ErrorCollector collector = new ErrorCollector();

  protected User createUser() {
    User user = new User();
    user.setFirstName(FIRST_NAME);
    user.setLastName(LAST_NAME);
    user.setEmail(EMAIL);
    user.setUsername(USERNAME);
    user.setPassword(PASSWORD);
    user.setSalary(SALARY);
    user.setToken(TOKEN);
    List<String> comments = new ArrayList<>();
    comments.add(COMMENT_1);
    comments.add(COMMENT_2);
    user.setComments(comments);
    user.setGender(MALE);
    user.setAddress(createAddress());
    return user;
  }

  protected PointResource createPointResource() {
    PointResource pointResource = new PointResource();
    pointResource.setGift(GIFT);
    pointResource.setValue(POINT_VALUE);
    return pointResource;
  }

  protected UserGetResource createUserGetResource() {
    UserGetResource getResource = new UserGetResource();
    getResource.setFirstName(FIRST_NAME);
    getResource.setLastName(LAST_NAME);
    getResource.setEmail(EMAIL);
    getResource.setUsername(USERNAME);
    getResource.setSalary(SALARY);
    List<String> comments = new ArrayList<>();
    comments.add(COMMENT_1);
    comments.add(COMMENT_2);
    getResource.setComments(comments);
    getResource.setGender(MALE);
    getResource.setAddress(createAddressResource());
    return getResource;
  }

  protected UserCreateResource createUserCreateResource() {
    UserCreateResource createResource = new UserCreateResource();
    createResource.setFirstName(FIRST_NAME);
    createResource.setLastName(LAST_NAME);
    createResource.setEmail(EMAIL);
    createResource.setUsername(USERNAME);
    createResource.setSalary(SALARY);
    List<String> comments = new ArrayList<>();
    comments.add(COMMENT_1);
    comments.add(COMMENT_2);
    createResource.setComments(comments);
    createResource.setGender(MALE);
    createResource.setPassword(PASSWORD);
    createResource.setAddress(createAddressResource());
    List<PointResource> points = new ArrayList<>();
    points.add(createPointResource());
    createResource.setPoints(points);
    Map<String, AddressResource> addressByState = new HashMap<>();
    addressByState.put(STATE_CODE, createAddressResource());
    createResource.setAddressByState(addressByState);
    return createResource;
  }

  protected Address createAddress() {
    Address address = new Address();
    address.setStreetName(STREET_NAME);
    address.setCountry(COUNTRY);
    address.setMap(createMapForAddress());
    return address;
  }

  protected AddressResource createAddressResource() {
    AddressResource resource = new AddressResource();
    resource.setStreetName(STREET_NAME);
    resource.setCountry(COUNTRY);
    resource.setMap(createMapForAddress());
    return resource;
  }

  protected Map<String, Integer> createMapForAddress() {
    Map<String, Integer> map = new HashMap<>();
    map.put(ONE_STRING, ONE_INTEGER);
    return map;
  }
}
