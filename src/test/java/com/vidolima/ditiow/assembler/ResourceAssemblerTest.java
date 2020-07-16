package com.vidolima.ditiow.assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.vidolima.ditiow.BaseTest;
import com.vidolima.ditiow.entity.ValueException;
import com.vidolima.ditiow.exception.IllegalCopyException;
import com.vidolima.ditiow.resource.AddressResource;
import com.vidolima.ditiow.resource.UserGetResource;

public class ResourceAssemblerTest extends BaseTest {

  private ResourceAssembler assembler;

  @Before
  public void setUp() {
    assembler = new ResourceAssembler();
  }

  @Test
  public void givenUserWhenAssemblyThenReturnUserGetResource() {
    // Act
    UserGetResource actual = assembler.assembly(createUser(), UserGetResource.class);

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
    AddressResource actualAddress = actual.getAddress();
    collector.checkThat(actualAddress.getCountry(), equalTo(COUNTRY));
    collector.checkThat(actualAddress.getStreetName(), equalTo(STREET_NAME));
    collector.checkThat(actualAddress.getMap().size(), equalTo(1));
    collector.checkThat(actualAddress.getMap().get(ONE_STRING), equalTo(ONE_INTEGER));
  }

  @Test
  public void givenNullWhenAssemblyThenReturnNull() {
    // Act & Assert
    assertNull(assembler.assembly(null, UserGetResource.class));
  }

  @Test
  public void givenNullTargetClassWhenAssemblyThenReturnNull() {
    // Act & Assert
    assertNull(assembler.assembly(new Object(), null));
  }

  @Test
  public void givenValueExceptionWhenAssemblyThenThrowIllegalCopyException() {
    try {
      assembler.assembly(new ValueException(), UserGetResource.class);
      fail("expected IllegalCopyException");
    } catch (IllegalCopyException e) {
      assertEquals("Could not copy some attribute values.", e.getMessage());
    }
  }
  
}
