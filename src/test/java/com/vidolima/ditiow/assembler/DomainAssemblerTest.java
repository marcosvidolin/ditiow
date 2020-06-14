package com.vidolima.ditiow.assembler;

import com.vidolima.ditiow.BaseTest;
import com.vidolima.ditiow.entity.Address;
import com.vidolima.ditiow.entity.User;
import com.vidolima.ditiow.entity.ValueException;
import com.vidolima.ditiow.exception.IllegalCopyException;
import com.vidolima.ditiow.resource.ValueExceptionResource;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DomainAssemblerTest extends BaseTest {

  private DomainAssembler assembler;

  @Before
  public void setUp() {
    assembler = new DomainAssembler();
  }

  @Test
  public void givenUserCreateResourceWhenAssemblyThenReturnUser() {
    // Act
    User actual = assembler.assembly(createUserCreateResource(), User.class);

    // Assert
    assertEquals(FIRST_NAME, actual.getFirstName());
    collector.checkThat(actual.getLastName(), equalTo(LAST_NAME));
    collector.checkThat(actual.getEmail(), equalTo(EMAIL));
    collector.checkThat(actual.getUsername(), equalTo(USERNAME));
    collector.checkThat(actual.getSalary(), equalTo(SALARY));
    collector.checkThat(actual.getGender(), equalTo(MALE));
    List<String> actualComments = actual.getComments();
    collector.checkThat(actualComments.size(), equalTo(2));
    collector.checkThat(actualComments.get(0), equalTo(COMMENT_1));
    collector.checkThat(actualComments.get(1), equalTo(COMMENT_2));
    Address actualAddress = actual.getAddress();
    collector.checkThat(actualAddress.getCountry(), equalTo(COUNTRY));
    collector.checkThat(actualAddress.getStreetName(), equalTo(STREET_NAME));
    collector.checkThat(actualAddress.getMap().size(), equalTo(1));
    collector.checkThat(actualAddress.getMap().get(ONE_STRING), equalTo(ONE_INTEGER));
    Map<String, Address> addressByState = actual.getAddressByState();
    Address hawaiiAddress = addressByState.get(STATE_CODE);
    collector.checkThat(hawaiiAddress.getCountry(), equalTo(COUNTRY));
    collector.checkThat(hawaiiAddress.getStreetName(), equalTo(STREET_NAME));
    collector.checkThat(hawaiiAddress.getMap().size(), equalTo(1));
    collector.checkThat(hawaiiAddress.getMap().get(ONE_STRING), equalTo(ONE_INTEGER));
  }

  @Test
  public void givenValueExceptionResourceWhenAssemblyThenThrowIllegalCopyException() {
    try {
      assembler.assembly(new ValueExceptionResource(), ValueException.class);
      fail("expected IllegalCopyException");
    } catch (IllegalCopyException e) {
      assertEquals("Could not create new instance of the target class.", e.getMessage());
    }
  }

  @Test
  public void givenNullObjectWhenAssemblyThenReturnNull() {
    // Act & Assert
    assertNull(assembler.assembly(null, User.class));
  }

  @Test
  public void givenNullTargetClassWhenAssemblyThenReturnNull() {
    // Act & Assert
    assertNull(assembler.assembly(new Object(), null));
  }
}
