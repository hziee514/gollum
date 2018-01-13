package org.gollum.common.exception;

/**
 * @author wurenhai
 * @date 2018/1/5
 */
public class ConcurrencyException extends RuntimeException {
    public ConcurrencyException(String format, Object...args) {
        super(String.format(format, args));
    }
}
