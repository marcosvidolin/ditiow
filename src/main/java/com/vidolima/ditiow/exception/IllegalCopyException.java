package com.vidolima.ditiow.exception;

/**
 * The class {@code IllegalCopyException} is used to indicates a
 * {@link RuntimeException} in a conversion.
 *
 * @author Marcos Vidolin
 * @since Dez 24, 2019
 */
public class IllegalCopyException extends RuntimeException {

  /**
   * Constructs a new exception with the specified detail message and
   * cause.  <p>Note that the detail message associated with
   * {@code cause} is <i>not</i> automatically incorporated in
   * this exception's detail message.
   *
   * @param  message the detail message (which is saved for later retrieval
   *         by the {@link #getMessage()} method).
   * @param  cause the cause (which is saved for later retrieval by the
   *         {@link #getCause()} method).  (A <b>null</b> value is
   *         permitted, and indicates that the cause is nonexistent or
   *         unknown.)
   * @since  1.4
   */
  public IllegalCopyException(String message, Throwable cause) {
    super(message, cause);
  }

}
