package io.github.rybot666.refutils;

/**
 * Exception thrown when a reflective error occurs.
 */
public class RefUtilsException extends ReflectiveOperationException {
    public RefUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RefUtilsException(String message) {
        super(message);
    }
}
