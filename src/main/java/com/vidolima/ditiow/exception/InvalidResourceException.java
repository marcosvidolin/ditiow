package com.vidolima.ditiow.exception;

import com.vidolima.ditiow.resource.Resource;

/**
 * The class {@code InvalidResourceException} is used to indicates a
 * {@link RuntimeException} when an invalid resource is used.
 *
 * @author Marcos Vidolin
 * @since Jan 16, 2019
 */
public class InvalidResourceException extends RuntimeException {

  /**
   * Constructs a new exception with the specified detail message.  The
   * cause is not initialized, and may subsequently be initialized by
   * a call to {@link #initCause}.
   *
   * @param   clazz   the invalid class
   */
  public InvalidResourceException(Class<?> clazz) {
    super("The class " + clazz.getName()
        + " found at @RequestResource annotation is not a valid resource."
        + " You must inform a subclass of " + Resource.class.getName());
  }

  /**
   * Constructs a new exception with the specified detail message.  The
   * cause is not initialized, and may subsequently be initialized by
   * a call to {@link #initCause}.
   *
   * @param   message   the detail message. The detail message is saved for
   *          later retrieval by the {@link #getMessage()} method.
   */
  public InvalidResourceException(String message) {
    super(message);
  }

  /**
   * Constructs a new exception with the specified detail message and
   * cause.  <p>Note that the detail message associated with
   * {@code cause} is <i>not</i> automatically incorporated in
   * this exception's detail message.
   *
   * @param  message the detail message (which is saved for later retrieval
   *         by the {@link #getMessage()} method).
   * @param  cause the cause (which is saved for later retrieval by the
   *         {@link #getCause()} method).  (A <tt>null</tt> value is
   *         permitted, and indicates that the cause is nonexistent or
   *         unknown.)
   * @since  1.4
   */
  public InvalidResourceException(String message, Throwable cause) {
    super(message, cause);
  }

}

