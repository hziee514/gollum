package org.gollum.core.domain;

/**
 * @author wurenhai
 * @date 2018/1/5
 */
public class ConcurrencyException extends RuntimeException {
    public ConcurrencyException(String message) {
        super(message);
    }
}
